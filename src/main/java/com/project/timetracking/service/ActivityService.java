package com.project.timetracking.service;

import com.project.timetracking.model.Activity;
import org.springframework.stereotype.Service;

@Service
public interface ActivityService {
    void create(Activity activity);

    Activity find(long id);

    Activity findByName(String name);

    void update(Activity activity);

    void delete(long id);

}
