package com.alumni.management.repository;

import com.alumni.management.entity.MentorshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MentorshipRequestRepository extends JpaRepository<MentorshipRequest, Long> {
    List<MentorshipRequest> findAllByOrderByCreatedAtDesc();
}