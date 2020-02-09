package com.project.timetracking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.timetracking.domain.enums.Role;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * The type User.
 */
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@Component
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "email"})
@NoArgsConstructor
@ToString(of = {"id", "email"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    private String email;

    @NotBlank
    @Size(max = 255)
    private String password;

    private String name;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_activities",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "activities_id")}
    )
    private Set<Activity> activitiesSet;

    @JsonIgnore
    @OneToMany(mappedBy = "userLog", fetch = FetchType.LAZY)
    private Set<ActivityLog> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "userOrder", fetch = FetchType.LAZY)
    private Set<Order> orders;

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param email    the email
     * @param password the password
     * @param name     the name
     */
    public User(long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
