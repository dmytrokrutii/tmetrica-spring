package com.project.timetracking.model.service.implementation;

import com.project.timetracking.model.domain.entity.Activity;
import com.project.timetracking.model.domain.entity.ActivityLog;
import com.project.timetracking.model.domain.entity.User;
import com.project.timetracking.model.domain.entity.dto.Statistic;
import com.project.timetracking.model.repository.ActivityLogRepository;
import com.project.timetracking.model.repository.StatisticRepository;
import com.project.timetracking.model.service.ActivityLogService;
import com.project.timetracking.model.service.ActivityService;
import com.project.timetracking.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The type Activity log service.
 */
@Service
@Slf4j
public class ActivityLogServiceImpl implements ActivityLogService {
    private final StatisticRepository statisticRepository;
    private final ActivityLogRepository activityLogRepository;
    private final UserService userService;
    private final ActivityService activityService;

    /**
     * Instantiates a new Activity log service.
     *
     * @param activityLogRepository the activity log repository
     * @param userService           the user service
     * @param activityService       the activity service
     * @param statisticRepository   the statistic repository
     */
    @Autowired
    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository, UserService userService, ActivityService activityService, StatisticRepository statisticRepository) {
        this.activityService = activityService;
        this.activityLogRepository = activityLogRepository;
        this.userService = userService;
        this.statisticRepository = statisticRepository;
    }


    @Override
    public void create(String startDate, String endDate, String username, long activityId) throws ParseException {
        User user = userService.findByEmail(username);
        Activity activity = activityService.find(activityId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        ActivityLog log = new ActivityLog(formatter.parse(startDate), formatter.parse(endDate), activity, user);
        ActivityLogServiceImpl.log.info("Creating activityLog {}", log);
        activityLogRepository.save(log);
    }

    @Override
    public void update(long id, String startDate, String endDate, String username, long activityId) throws ParseException {
        User user = userService.findByEmail(username);
        Activity activity = activityService.find(activityId);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        ActivityLog log = new ActivityLog(id, activity, user, formatter.parse(startDate), formatter.parse(endDate));
        ActivityLogServiceImpl.log.info("Updating activityLog {}", log);
        activityLogRepository.save(log);
    }

    @Override
    public void deleteById(long id) {
        log.info("Deleting activityLog with id {}", id);
        activityLogRepository.deleteById(id);
    }

    @Override
    public Page<Statistic> getStatisticByUserEmail(String email, Pageable pageable) {
        long userId = userService.findByEmail(email).getId();
        log.info("Get statistic for user with id {}", userId);
        return statisticRepository.findAllByUserId(userId, pageable);
    }


    @Override
    public Page<ActivityLog> findLogsByUserEmail(String email, Pageable pageable) {
        User user = userService.findByEmail(email);
        log.info("Find activityLogs for user with id {}", user.getId());
        return activityLogRepository.findByUserLog(user, pageable);
    }

    @Override
    public Page<ActivityLog> findByUserAndActivity(String email, long acidityId, Pageable pageable) {
        log.info("Find activityLog with email {}", email);
        return activityLogRepository.findByUserLogAndActivityLog(userService.findByEmail(email), activityService.find(acidityId), pageable);
    }
}
