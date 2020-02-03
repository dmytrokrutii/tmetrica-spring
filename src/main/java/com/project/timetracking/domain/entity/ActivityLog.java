package com.project.timetracking.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Activity log.
 */
@Entity
@Table(name = "activities_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "closingTime"})
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activityLog;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userLog;

    @Column(name = "start_time")
    private Date startTime;

    /**
     * Instantiates a new Activity log.
     *
     * @param startTime the start time
     * @param endTime   the end time
     * @param activity  the activity
     * @param user      the user
     */
    public ActivityLog(Date startTime, Date endTime, Activity activity, User user) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityLog = activity;
        this.userLog = user;
    }

    @Column(name = "end_time")
    private Date endTime;


}
