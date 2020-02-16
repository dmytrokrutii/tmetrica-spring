package com.project.timetracking.service;

import com.project.timetracking.model.domain.entity.Activity;
import com.project.timetracking.model.domain.entity.Order;
import com.project.timetracking.model.domain.entity.User;
import com.project.timetracking.model.domain.enums.Action;
import com.project.timetracking.model.domain.enums.ActivityStatus;
import com.project.timetracking.model.domain.enums.OrderStatus;
import com.project.timetracking.model.repository.ActivityRepository;
import com.project.timetracking.model.repository.OrderRepository;
import com.project.timetracking.model.repository.UserRepository;
import com.project.timetracking.model.service.ActivityService;
import com.project.timetracking.model.service.OrderService;
import com.project.timetracking.model.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * The type Order service impl test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @MockBean
    private ActivityService activityService;

    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ActivityRepository activityRepository;


    @Test
    public void shouldReturnOrderWithStatusPendingAndActionCreate() {
        Activity activity = new Activity(1, "Name", new Date(), new Date(), ActivityStatus.SUSPENDED, null, null, null);
        Order order = new Order(activity, new User(), OrderStatus.PENDING, Action.CREATE);
        User user = new User(1, "email@test", "password", "Name");
        Mockito.doReturn(user)
                .when(userRepository)
                .findByEmail("email@test");

        Mockito.doReturn(activity)
                .when(activityRepository).save(activity);

        Mockito.doReturn(activity)
                .when(activityRepository)
                .findById(1L);

        Mockito.doReturn(activity)
                .when(activityService)
                .find(1L);

        Mockito.doNothing()
                .when(activityService)
                .addUserToActivity(user, activity);

        Mockito.doReturn(order)
                .when(orderRepository)
                .save(order);


        Order actual = orderService.crateNewActivityOrder("Name", "email@test");
        assertEquals(order, actual);
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(ArgumentMatchers.anyString());
        Mockito.verify(activityRepository, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
        Mockito.verify(activityRepository, Mockito.times(1)).save(ArgumentMatchers.any(Activity.class));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any(Order.class));

    }

    private void assertEquals(Order order, Order actual) {
    }

    @Test
    public void createJoinToActivityOrder() {
    }

    @Test
    public void createDeleteActivityOrder() {
    }

    @Test
    public void approveOrder() {
    }

    @Test
    public void rejectOrder() {
    }
}