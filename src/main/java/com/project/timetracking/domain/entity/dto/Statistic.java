package com.project.timetracking.domain.entity.dto;

import com.project.timetracking.domain.enums.ActivityStatus;
import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Duration;

/**
 * The type Statistic.
 */
@TypeDef(
        typeClass = PostgreSQLIntervalType.class,
        defaultForType = Duration.class
)

@Entity(name = "stats_view")
@Immutable
@Data
public class Statistic {
    @Id
    @Column(updatable = false, nullable = false)
    private Long activityId;

    @Column
    private String activityName;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ActivityStatus activityStatus;

    @Column
    private Duration time;

    private long userId;

    /**
     * Format duration string.
     *
     * @return the string
     */
    public String formatDuration() {
        long days = time.toDays();
        time = time.minusDays(days);
        long hours = time.toHours();
        time = time.minusHours(hours);
        long minutes = time.toMinutes();
        time = time.minusMinutes(minutes);
        long seconds = time.getSeconds();
        return
                (days == 0 ? "" : days + " days ") +
                        (hours == 0 ? "" : hours + " hours ") +
                        (minutes == 0 ? "" : minutes + " minutes ") +
                        (seconds == 0 ? "" : seconds + " seconds");
    }

}
