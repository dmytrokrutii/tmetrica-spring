package com.project.timetracking.contoller;

import com.project.timetracking.domain.entity.Activity;
import com.project.timetracking.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

/**
 * The type Activity controller.
 */
@Controller
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;
    private final static String ACTIVITIES = "activities";
    private final static String PAGES_NUMBER = "numberPages";

    /**
     * Instantiates a new Activity controller.
     *
     * @param activityService the activity service
     */
    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * Gets opened activities.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param pageable      the pageable
     * @return the opened activities
     */
    @GetMapping()
    public String getOpenedActivities(@AuthenticationPrincipal UserDetails userPrincipal, Model model, @PageableDefault(sort = {"startDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Activity> pages = activityService.findAvailableActivitiesByUserEmail(pageable, userPrincipal.getUsername());
        model.addAttribute(ACTIVITIES, pages.get().collect(Collectors.toList()));
        model.addAttribute(PAGES_NUMBER, pages.getTotalPages());
        return ACTIVITIES;
    }

    /**
     * Gets user activities.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param pageable      the pageable
     * @return the user activities
     */
    @GetMapping("/my")
    public String getUserActivities(@AuthenticationPrincipal UserDetails userPrincipal, Model model, @PageableDefault(sort = {"startDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Activity> pages = activityService.findActivitiesByUserEmail(pageable, userPrincipal.getUsername());
        model.addAttribute(ACTIVITIES, pages.get().collect(Collectors.toList()));
        model.addAttribute(PAGES_NUMBER, pages.getTotalPages());
        model.addAttribute("usersList", true);
        return ACTIVITIES;
    }
}
