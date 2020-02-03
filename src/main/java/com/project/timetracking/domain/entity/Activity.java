package com.project.timetracking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.timetracking.domain.enums.ActivityStatus;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

/**
 * The type Activity.
 */
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotNull
    @Column(name = "opening_time")
    private Date startDate;

    @Column(name = "closing_time")
    private Date endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ActivityStatus status;


    @JsonIgnore
    @ManyToMany(mappedBy = "activitiesSet", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "activityLog", fetch = FetchType.LAZY)
    private Set<ActivityLog> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "activityOrder", fetch = FetchType.LAZY)
    private Set<Order> orders;

    /**
     * Instantiates a new Activity.
     *
     * @param name      the name
     * @param startDate the start date
     * @param status    the status
     */
    public Activity(String name, Date startDate, ActivityStatus status) {
        this.name = name;
        this.startDate = startDate;
        this.status = status;
    }


}
