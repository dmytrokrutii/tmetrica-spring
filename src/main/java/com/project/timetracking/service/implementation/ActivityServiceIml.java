package com.project.timetracking.service.implementation;

import com.project.timetracking.model.Activity;
import com.project.timetracking.repository.ActivityRepository;
import com.project.timetracking.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityServiceIml implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

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
}
