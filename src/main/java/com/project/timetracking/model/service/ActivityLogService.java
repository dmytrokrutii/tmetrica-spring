package com.project.timetracking.model.service;

import com.project.timetracking.model.domain.entity.ActivityLog;
import com.project.timetracking.model.domain.entity.dto.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * The interface Activity log service.
 */
@Service
public interface ActivityLogService {

    /**
     * Create.
     *
     * @param startDate  the start date
     * @param endDate    the end date
     * @param username   the username
     * @param activityId the activity id
     * @throws ParseException the parse exception
     */
    void create(String startDate, String endDate, String username, long activityId) throws ParseException;

    /**
     * Update.
     *
     * @param id         the id
     * @param startDate  the start date
     * @param endDate    the end date
     * @param username   the username
     * @param activityId the activity id
     * @throws ParseException the parse exception
     */
    void update(long id, String startDate, String endDate, String username, long activityId) throws ParseException;

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(long id);

    /**
     * Gets statistic by user email.
     *
     * @param email    the email
     * @param pageable the pageable
     * @return the statistic by user email
     */
    Page<Statistic> getStatisticByUserEmail(String email, Pageable pageable);

    /**
     * Find logs by user email.
     *
     * @param email    the email
     * @param pageable the pageable
     * @return the page
     */
    Page<ActivityLog> findLogsByUserEmail(String email, Pageable pageable);

    /**
     * Find by user and activity.
     *
     * @param email     the email
     * @param acidityId the acidity id
     * @param pageable  the pageable
     * @return the page
     */
    Page<ActivityLog> findByUserAndActivity(String email, long acidityId, Pageable pageable);

}
