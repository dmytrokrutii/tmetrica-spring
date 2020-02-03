package com.project.timetracking.repository;

import com.project.timetracking.domain.entity.dto.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Statistic repository.
 */
@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    /**
     * Find all by user id page.
     *
     * @param userId   the user id
     * @param pageable the pageable
     * @return the page
     */
    Page<Statistic> findAllByUserId(long userId, Pageable pageable);

}
