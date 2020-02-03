package com.project.timetracking.service.implementation;

import com.project.timetracking.domain.entity.Activity;
import com.project.timetracking.domain.entity.ActivityLog;
import com.project.timetracking.repository.ActivityRepository;
import com.project.timetracking.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public void create(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public Activity find(long id) {
        return activityRepository.findById(id);
    }

    @Override
    public Activity findByName(String name) {
        return activityRepository.findByName(name);
    }

    @Override
    public void update(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void delete(long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Set<ActivityLog> getLogs(long id) {
        return activityRepository.findById(id).getLogs();
    }
}
