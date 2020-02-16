package com.project.timetracking.model.service.implementation;

import com.project.timetracking.model.domain.entity.Activity;
import com.project.timetracking.model.domain.entity.User;
import com.project.timetracking.model.domain.enums.ActivityStatus;
import com.project.timetracking.model.repository.ActivityRepository;
import com.project.timetracking.model.service.ActivityService;
import com.project.timetracking.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Activity service.
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final UserService userService;

    /**
     * Instantiates a new Activity service.
     *
     * @param activityRepository the activity repository
     * @param userService        the user service
     */
    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, UserService userService) {
        this.userService = userService;
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity createAdReturn(Activity activity) {
        log.info("Create activity {}", activity);
        return activityRepository.save(activity);
    }

    @Override
    public Activity find(long id) {
        log.info("Find activity by id {}", id);
        return activityRepository.findById(id);
    }

    @Override
    public Activity updateAndReturn(Activity activity) {
        log.info("Update activity{}", activity);
        return activityRepository.save(activity);
    }

    @Override
    public void delete(Activity activity) {
        log.info("Deleting activity{}", activity);
        activityRepository.delete(activity);
    }

    @Override
    public void delete(long id) {
        log.info("Deleting activity by id {}", id);
        activityRepository.deleteById(id);
    }

    @Override
    public void addUserToActivity(User user, Activity activity) {
        log.info("Add user {} to activity {}", user, activity);
        activityRepository.addUserToActivity(user.getId(), activity.getId());
    }

    @Override
    public void deleteUserFromActivity(User user, Activity activity) {
        log.info("Delete user {} from activity {}", user, activity);
        activityRepository.deleteUserFromActivity(user.getId(), activity.getId());
    }

    @Override
    public List<Activity> findActiveActivityByUserEmail(String email) {
        log.info("Find users active activities by email {}", email);
        return activityRepository.findAllByStatusAndUsers(ActivityStatus.ACTIVE, userService.findByEmail(email));
    }

    @Override
    public Page<Activity> findAvailableActivitiesByUserEmail(Pageable pageable, String userEmail) {
        log.info("Find all activities with ACTIVE status that do not participate in orders with status PENDING by userEmail {}", userEmail);
        return new PageImpl<>(activityRepository.findAllByStatus(ActivityStatus.ACTIVE, pageable).stream()
                .filter(e -> e.getUsers()
                        .stream().map(User::getEmail)
                        .noneMatch(userEmail::equals)).collect(Collectors.toList()));
    }

    @Override
    public Page<Activity> findActivitiesByUserEmail(Pageable pageable, String userEmail) {
        log.info("Find all activities by user email {}", userEmail);
        return activityRepository.findAllByUsers(userService.findByEmail(userEmail), pageable);
    }
}
