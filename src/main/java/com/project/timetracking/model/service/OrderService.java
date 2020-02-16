package com.project.timetracking.model.service;

import com.project.timetracking.model.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * The interface Order service.
 */
@Service
public interface OrderService {

    /**
     * Gets all pending order.
     *
     * @param pageable the pageable
     * @return the all pending order
     */
    Page<Order> getAllPendingOrder(Pageable pageable);

    /**
     * Gets order history.
     *
     * @param pageable the pageable
     * @return the order history
     */
    Page<Order> getOrderHistory(Pageable pageable);

    /**
     * Crate new activity order order.
     *
     * @param activityName the activity name
     * @param email        the email
     * @return the order
     */
    Order crateNewActivityOrder(String activityName, String email);

    /**
     * Create join to activity order order.
     *
     * @param email      the email
     * @param activityId the activity id
     * @return the order
     */
    Order createJoinToActivityOrder(String email, long activityId);

    /**
     * Create delete activity order order.
     *
     * @param email      the email
     * @param activityId the activity id
     * @return the order
     */
    Order createDeleteActivityOrder(String email, long activityId);

    /**
     * Find order.
     *
     * @param id the id
     * @return the order
     */
    Order find(long id);

    /**
     * Approve order.
     *
     * @param order the order
     */
    void approveOrder(Order order);

    /**
     * Reject order.
     *
     * @param order the order
     */
    void rejectOrder(Order order);

}
