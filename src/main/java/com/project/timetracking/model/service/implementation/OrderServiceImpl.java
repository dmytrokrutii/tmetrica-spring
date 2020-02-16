package com.project.timetracking.model.service.implementation;

import com.project.timetracking.model.domain.entity.Activity;
import com.project.timetracking.model.domain.entity.Order;
import com.project.timetracking.model.domain.entity.User;
import com.project.timetracking.model.domain.enums.Action;
import com.project.timetracking.model.domain.enums.ActivityStatus;
import com.project.timetracking.model.domain.enums.OrderStatus;
import com.project.timetracking.model.repository.OrderRepository;
import com.project.timetracking.model.service.ActivityService;
import com.project.timetracking.model.service.OrderService;
import com.project.timetracking.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * The type Order service.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ActivityService activityService;

    /**
     * Instantiates a new Order service.
     *
     * @param orderRepository the order repository
     * @param userService     the user service
     * @param activityService the activity service
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.activityService = activityService;
    }

    @Override
    public Page<Order> getAllPendingOrder(Pageable pageable) {
        log.info("Get all orders with status PENDING");
        return orderRepository.findAllByStatus(OrderStatus.PENDING, pageable);
    }

    @Override
    public Page<Order> getOrderHistory(Pageable pageable) {
        log.info("Get all orders with statuses PENDING");
        return orderRepository.findAllByStatusIsNot(OrderStatus.PENDING, pageable);
    }

    @Override
    public Order crateNewActivityOrder(String activityName, String email) {
        User user = userService.findByEmail(email);
        Activity activity = activityService.createAdReturn(new Activity(activityName, new Date(), ActivityStatus.SUSPENDED));
        activityService.addUserToActivity(user, activity);
        Order order = new Order(activityService.find(activity.getId()), user, OrderStatus.PENDING, Action.CREATE);
        log.info("Create order on creating activity {}", activity);
        return orderRepository.save(order);
    }

    @Override
    public Order createJoinToActivityOrder(String email, long activityId) {
        User user = userService.findByEmail(email);
        Activity activity = activityService.find(activityId);
        Order order = new Order(activity, user, OrderStatus.PENDING, Action.JOIN);
        log.info("Create order on joining user{} to activity {}", user, activity);
        return orderRepository.save(order);
    }

    @Override
    public Order createDeleteActivityOrder(String email, long activityId) {
        User user = userService.findByEmail(email);
        Activity activity = activityService.find(activityId);
        Order order = new Order(activity, user, OrderStatus.PENDING, Action.DELETE);
        log.info("Create order on deleting user{} from activity {}", user, activity);
        return orderRepository.save(order);
    }

    @Override
    public Order find(long id) {
        log.info("Find order by id {}", id);
        return orderRepository.findById(id);
    }

    @Override
    public void approveOrder(Order order) {
        Activity activity = order.getActivityOrder();
        User user = order.getUserOrder();
        if (order.getAction().equals(Action.CREATE)) {
            prepareCreateOrderToApprove(order, activity);
        } else if (order.getAction().equals(Action.JOIN)) {
            prepareJoinOrderToApprove(order, activity, user);
        } else {
            if (lastUserInActivity(activity)) {
                return;
            }
            prepareDeleteOrderToApprove(order, activity, user);
        }
        order.setStatus(OrderStatus.ACCEPTED);
        log.info("Approve order {}", order);
        orderRepository.save(order);
    }

    @Override
    public void rejectOrder(Order order) {
        Activity activity = order.getActivityOrder();
        if (order.getAction().equals(Action.CREATE)) {
            activityService.delete(activity);
        } else {
            order.setStatus(OrderStatus.REJECTED);
            orderRepository.save(order);
        }
        log.info("Reject order {}", order);
    }


    private void prepareCreateOrderToApprove(Order order, Activity activity) {
        activity.setStatus(ActivityStatus.ACTIVE);
        Activity activityToSave = activityService.updateAndReturn(activity);
        order.setActivityOrder(activityToSave);
    }

    private void prepareJoinOrderToApprove(Order order, Activity activity, User user) {
        activityService.addUserToActivity(user, activity);
        order.setActivityOrder(activityService.find(activity.getId()));
    }

    private void prepareDeleteOrderToApprove(Order order, Activity activity, User user) {
        activityService.deleteUserFromActivity(user, activity);
        order.setActivityOrder(activityService.find(activity.getId()));
    }

    private boolean lastUserInActivity(Activity activity) {
        if (activity.getUsers().size() <= 1) {
            activityService.delete(activity.getId());
            return true;
        }
        return false;
    }

}
