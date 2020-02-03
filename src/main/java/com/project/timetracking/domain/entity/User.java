package com.project.timetracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.timetracking.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

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
    @OneToMany(mappedBy = "userLog")
    private Set<ActivityLog> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "userOrder")
    private Set<Order> orders;


}
