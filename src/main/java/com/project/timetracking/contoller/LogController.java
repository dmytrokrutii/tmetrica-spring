package com.project.timetracking.contoller;

import com.project.timetracking.domain.entity.ActivityLog;
import com.project.timetracking.domain.entity.dto.Statistic;
import com.project.timetracking.service.ActivityLogService;
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
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.stream.Collectors;

/**
 * The type Log controller.
 */
@Controller
@RequestMapping("/times")
public class LogController {
    private final ActivityLogService activityLogService;
    private final ActivityService activityService;
    private final static String TIMES_PAGE = "times";
    private final static String STATISTIC_PAGE = "statistic";

    /**
     * Instantiates a new Log controller.
     *
     * @param activityLogService the activity log service
     * @param activityService    the activity service
     */
    @Autowired
    public LogController(ActivityLogService activityLogService, ActivityService activityService) {
        this.activityLogService = activityLogService;
        this.activityService = activityService;
    }

    /**
     * Gets user logs.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param pageable      the pageable
     * @return the user logs
     */
    @GetMapping()
    public String getUserLogs(@AuthenticationPrincipal UserDetails userPrincipal, Model model, @PageableDefault(sort = {"startTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ActivityLog> pages = activityLogService.findLogsByUserEmail(userPrincipal.getUsername(), pageable);
        model.addAttribute("logs", pages.get().collect(Collectors.toList()));
        model.addAttribute("activities", activityService.findActiveActivityByUserEmail(userPrincipal.getUsername()));
        model.addAttribute("numberPages", pages.getTotalPages());
        return TIMES_PAGE;
    }

    /**
     * Add new log string.
     *
     * @param userPrincipal the user principal
     * @param startDate     the start date
     * @param endDate       the end date
     * @param activityId    the activity id
     * @return the string
     * @throws ParseException the parse exception
     */
    @PostMapping("/add")
    public String addNewLog(@AuthenticationPrincipal UserDetails userPrincipal, @RequestParam String startDate,
                            @RequestParam String endDate, @RequestParam int activityId) throws ParseException {
        activityLogService.create(startDate, endDate, userPrincipal.getUsername(), activityId);
        return "redirect:/times";
    }

    /**
     * Delete log string.
     *
     * @param logId the log id
     * @return the string
     */
    @PostMapping("/delete")
    public String deleteLog(@RequestParam long logId) {
        activityLogService.deleteById(logId);
        return "redirect:/times";
    }

    /**
     * Update log string.
     *
     * @param userPrincipal the user principal
     * @param startDate     the start date
     * @param endDate       the end date
     * @param activityId    the activity id
     * @param logId         the log id
     * @return the string
     * @throws ParseException the parse exception
     */
    @PostMapping("/edit")
    public String updateLog(@AuthenticationPrincipal UserDetails userPrincipal, @RequestParam String startDate,
                            @RequestParam String endDate, @RequestParam int activityId, @RequestParam long logId) throws ParseException {
        activityLogService.update(logId, startDate, endDate, userPrincipal.getUsername(), activityId);
        return "redirect:/times";
    }

    /**
     * Gets logs by activity.
     *
     * @param userPrincipal the user principal
     * @param id            the id
     * @param model         the model
     * @param pageable      the pageable
     * @return the logs by activity
     */
    @GetMapping("/acivity/{id}")
    public String getLogsByActivity(@AuthenticationPrincipal UserDetails userPrincipal, @PathVariable long id, Model model, @PageableDefault(sort = {"startTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ActivityLog> pages = activityLogService.findByUserAndActivity(userPrincipal.getUsername(), id, pageable);
        model.addAttribute("logs", pages.get().collect(Collectors.toList()));
        model.addAttribute("activities", activityService.findActiveActivityByUserEmail(userPrincipal.getUsername()));
        model.addAttribute("numberPages", pages.getTotalPages());
        return TIMES_PAGE;
    }

    /**
     * Gets statistic by user.
     *
     * @param userPrincipal the user principal
     * @param pageable      the pageable
     * @param model         the model
     * @return the statistic by user
     */
    @GetMapping("/statistic")
    public String getStatisticByUser(@AuthenticationPrincipal UserDetails userPrincipal, Pageable pageable, Model model) {
        Page<Statistic> pages = activityLogService.getStatisticByUserEmail(userPrincipal.getUsername(), pageable);
        model.addAttribute("stats", pages.get().collect(Collectors.toList()));
        model.addAttribute("numberPages", pages.getTotalPages());
        return STATISTIC_PAGE;
    }
}
