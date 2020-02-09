package com.project.timetracking.repository;

import com.project.timetracking.domain.entity.Activity;
import com.project.timetracking.domain.entity.ActivityLog;
import com.project.timetracking.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Activity log repository.
 */
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    /**
     * Find by user log page.
     *
     * @param user     the user
     * @param pageable the pageable
     * @return the page
     */
    Page<ActivityLog> findByUserLog(User user, Pageable pageable);

    /**
     * Find by user log and activity log page.
     *
     * @param user     the user
     * @param activity the activity
     * @param pageable the pageable
     * @return the page
     */
    Page<ActivityLog> findByUserLogAndActivityLog(User user, Activity activity, Pageable pageable);

}
