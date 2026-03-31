package com.chaersi.mode_api.repository;

import com.chaersi.mode_api.entity.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    @Query(value = "SELECT application_id FROM application_requests WHERE application_id LIKE CONCAT(:date, '%') ORDER BY application_id DESC LIMIT 1", nativeQuery = true)
    Optional<String> findLastApplicationIdForDate(@Param("date") String datePrefix);
}
