package com.project.timetracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.timetracking.model.enums.Role;
import com.project.timetracking.util.PostgreSQLEnumType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "login", "email"})
@NoArgsConstructor
@ToString(of = {"id", "login", "email"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    private String email;

    @NotBlank
    @Size(max = 100, min = 5)
    private String login;

    @NotBlank
    @Size(max = 255)
    private String password;

    private String name;

    private String surname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Role role;

    private boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_activities",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "activities_id")}
    )
    private Set<Activity> activitiesSet;

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = Role.USER;
        this.enabled = true;
    }
    
}
