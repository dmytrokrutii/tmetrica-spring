package com.project.timetracking.contoller;

import com.project.timetracking.model.Activity;
import com.project.timetracking.service.ActivityService;
import com.project.timetracking.wrapper.GeneralResponseWrapper;
import com.project.timetracking.wrapper.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponseWrapper<Activity> getByEMail(@PathVariable long id) {
        return new GeneralResponseWrapper(Status.of(HttpStatus.OK), activityService.find(id));
    }
}
