package com.project.timetracking.model.repository;

import com.project.timetracking.model.domain.entity.Activity;
import com.project.timetracking.model.domain.entity.User;
import com.project.timetracking.model.domain.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The interface Activity repository.
 */
@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    /**
     * Find by id activity.
     *
     * @param id the id
     * @return the activity
     */
    Activity findById(long id);

    /**
     * Find all by status and users list.
     *
     * @param status the status
     * @param user   the user
     * @return the list
     */
    List<Activity> findAllByStatusAndUsers(ActivityStatus status, User user);

    /**
     * Find all by status page.
     *
     * @param status   the status
     * @param pageable the pageable
     * @return the page
     */
    Page<Activity> findAllByStatus(ActivityStatus status, Pageable pageable);

    /**
     * Find all by users page.
     *
     * @param user     the user
     * @param pageable the pageable
     * @return the page
     */
    Page<Activity> findAllByUsers(User user, Pageable pageable);

    /**
     * Add user to activity.
     *
     * @param userId     the user id
     * @param activityId the activity id
     */
    @Modifying
    @Query(value = "insert into users_activities (user_id, activities_id) VALUES (?1, ?2)", nativeQuery = true)
    void addUserToActivity(long userId, long activityId);

    /**
     * Delete user from activity.
     *
     * @param id  the id
     * @param id1 the id 1
     */
    @Modifying
    @Query(value = "START TRANSACTION ; " +
            "SET CONSTRAINTS ALL DEFERRED ; " +
            "DELETE FROM users_activities where user_id = ?1 and activities_id = ?2 ; " +
            "COMMIT TRANSACTION; ", nativeQuery = true)
    void deleteUserFromActivity(long id, long id1);
}
