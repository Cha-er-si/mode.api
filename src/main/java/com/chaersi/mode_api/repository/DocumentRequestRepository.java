package com.chaersi.mode_api.repository;

import com.chaersi.mode_api.entity.DocumentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRequestRepository extends JpaRepository<DocumentRequest, Long> {
    Optional<DocumentRequest> findByReceptionNumber(String receptionNumber);
}
