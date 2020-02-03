package com.project.timetracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.timetracking.model.enums.ActivityStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "name"})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(name = "opening_time")
    private Date startDate;

    @NotBlank
    @Column(name = "closing_time")
    private Date endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActivityStatus status;

    @JsonIgnore
    @ManyToMany(mappedBy = "activitiesSet", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "activityLog")
    private Set<ActivityLog> logs;

    @JsonIgnore
    @OneToMany(mappedBy = "activityOrder")
    private Set<Order> orders;

}
