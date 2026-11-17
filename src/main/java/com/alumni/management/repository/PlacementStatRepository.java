package com.alumni.management.repository;

import com.alumni.management.entity.PlacementStat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlacementStatRepository extends JpaRepository<PlacementStat, Long> {
    List<PlacementStat> findAllByOrderByYearDesc();
}