package com.project.timetracking.controller;

import com.project.timetracking.model.domain.entity.Order;
import com.project.timetracking.model.security.UserDetailsServiceImpl;
import com.project.timetracking.model.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

/**
 * The type Order controller.
 */
@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final static String ORDERS = "orders";

    /**
     * Instantiates a new Order controller.
     *
     * @param orderService the order service
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create new activity order string.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param activityName  the activity name
     * @return the string
     */
    @PostMapping("/new")
    public String createNewActivityOrder(@AuthenticationPrincipal UserDetails userPrincipal, Model model, @RequestParam String activityName) {
        Order order = orderService.crateNewActivityOrder(activityName, userPrincipal.getUsername());
        if (UserDetailsServiceImpl.hasAdminAuthority(userPrincipal)) {
            orderService.approveOrder(order);
        }
        return "redirect:/activities";
    }

    /**
     * Gets order history.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param pageable      the pageable
     * @return the order history
     */
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getOrderHistory(@AuthenticationPrincipal UserDetails userPrincipal, Model model, Pageable pageable) {
        Page<Order> pages = orderService.getOrderHistory(pageable);
        model.addAttribute("orders", pages.get().collect(Collectors.toList()));
        model.addAttribute("active", false);
        model.addAttribute("numberPages", pages.getTotalPages());
        return ORDERS;
    }

    /**
     * Create join to activity order string.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param activityId    the activity id
     * @return the string
     */
    @PostMapping("/join")
    public String createJoinToActivityOrder(@AuthenticationPrincipal UserDetails userPrincipal, Model model, @RequestParam long activityId) {
        Order order = orderService.createJoinToActivityOrder(userPrincipal.getUsername(), activityId);
        if (UserDetailsServiceImpl.hasAdminAuthority(userPrincipal)) {
            orderService.approveOrder(order);
        }
        return "redirect:/activities";
    }

    /**
     * Create delete from activity order string.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param activityId    the activity id
     * @return the string
     */
    @PostMapping("/delete")
    public String createDeleteFromActivityOrder(@AuthenticationPrincipal UserDetails userPrincipal, Model model, @RequestParam long activityId) {
        Order order = orderService.createDeleteActivityOrder(userPrincipal.getUsername(), activityId);
        if (UserDetailsServiceImpl.hasAdminAuthority(userPrincipal)) {
            orderService.approveOrder(order);
        }
        return "redirect:/activities/my";
    }


    /**
     * Gets all active order.
     *
     * @param userPrincipal the user principal
     * @param model         the model
     * @param pageable      the pageable
     * @return the all active order
     */
    @GetMapping("/active")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllActiveOrder(@AuthenticationPrincipal UserDetails userPrincipal, Model model, Pageable pageable) {
        Page<Order> pages = orderService.getAllPendingOrder(pageable);
        model.addAttribute(ORDERS, pages.get().collect(Collectors.toList()));
        model.addAttribute("active", true);
        model.addAttribute("numberPages", pages.getTotalPages());
        return ORDERS;
    }

    /**
     * Accept order string.
     *
     * @param orderId the order id
     * @return the string
     */
    @PostMapping("/accept")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String acceptOrder(@RequestParam long orderId) {
        orderService.approveOrder(orderService.find(orderId));
        return "redirect:/orders/active";
    }

    /**
     * Reject order string.
     *
     * @param orderId the order id
     * @return the string
     */
    @PostMapping("/reject")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String rejectOrder(@RequestParam long orderId) {
        orderService.rejectOrder(orderService.find(orderId));
        return "redirect:/orders/active";
    }
}
