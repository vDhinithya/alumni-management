package com.alumni.management.repository;

import com.alumni.management.entity.EmploymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Long> {
}