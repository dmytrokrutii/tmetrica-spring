package com.project.timetracking.repository;

import com.project.timetracking.domain.entity.Order;
import com.project.timetracking.domain.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Order repository.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Find all by status page.
     *
     * @param status   the status
     * @param pageable the pageable
     * @return the page
     */
    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);

    /**
     * Find all by status is not page.
     *
     * @param status   the status
     * @param pageable the pageable
     * @return the page
     */
    Page<Order> findAllByStatusIsNot(OrderStatus status, Pageable pageable);

    /**
     * Find by id order.
     *
     * @param id the id
     * @return the order
     */
    Order findById(long id);
}
