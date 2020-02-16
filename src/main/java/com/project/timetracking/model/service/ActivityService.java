package com.project.timetracking.model.service;

import com.project.timetracking.model.domain.entity.Activity;
import com.project.timetracking.model.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The interface Activity service.
 */
public interface ActivityService {

    /**
     * Create ad return activity.
     *
     * @param activity the activity
     * @return the activity
     */
    Activity createAdReturn(Activity activity);

    /**
     * Find activity.
     *
     * @param id the id
     * @return the activity
     */
    Activity find(long id);

    /**
     * Update and return activity.
     *
     * @param activity the activity
     * @return the activity
     */
    Activity updateAndReturn(Activity activity);

    /**
     * Delete.
     *
     * @param activity the activity
     */
    void delete(Activity activity);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id);

    /**
     * Add user to activity.
     *
     * @param user     the user
     * @param activity the activity
     */
    void addUserToActivity(User user, Activity activity);

    /**
     * Delete user from activity.
     *
     * @param user     the user
     * @param activity the activity
     */
    void deleteUserFromActivity(User user, Activity activity);

    /**
     * Find active activity by user email list.
     *
     * @param email the email
     * @return the list
     */
    List<Activity> findActiveActivityByUserEmail(String email);

    /**
     * Find available activities by user email page.
     *
     * @param pageable  the pageable
     * @param userEmail the user email
     * @return the page
     */
    Page<Activity> findAvailableActivitiesByUserEmail(Pageable pageable, String userEmail);

    /**
     * Find activities by user email page.
     *
     * @param pageable  the pageable
     * @param userEmail the user email
     * @return the page
     */
    Page<Activity> findActivitiesByUserEmail(Pageable pageable, String userEmail);

}
