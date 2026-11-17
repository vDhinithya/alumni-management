package com.alumni.management.repository;

import com.alumni.management.entity.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {
    List<AlumniProfile> findAll();
}