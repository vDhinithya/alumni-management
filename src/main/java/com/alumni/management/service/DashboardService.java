package com.alumni.management.service;

import com.alumni.management.entity.PlacementStat;
import com.alumni.management.entity.UserRole;
import com.alumni.management.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final EmploymentHistoryRepository employmentHistoryRepository;
    private final MentorshipRequestRepository mentorshipRequestRepository;
    private final PlacementStatRepository placementStatRepository;

    public DashboardService(UserRepository userRepository,
                            EmploymentHistoryRepository employmentHistoryRepository,
                            MentorshipRequestRepository mentorshipRequestRepository,
                            PlacementStatRepository placementStatRepository) {
        this.userRepository = userRepository;
        this.employmentHistoryRepository = employmentHistoryRepository;
        this.mentorshipRequestRepository = mentorshipRequestRepository;
        this.placementStatRepository = placementStatRepository;
    }

    public long getRegisteredAlumniCount() {
        return userRepository.count();
    }

    public long getEmploymentRecordsCount() {
        return employmentHistoryRepository.count();
    }

    public long getMentorshipRequestsCount() {
        return mentorshipRequestRepository.count();
    }

    public long getPlacementRecordsCount() {
        return placementStatRepository.count();
    }

    public long getAdminCount() {
        return userRepository.findAll().stream().filter(u -> u.getRole() == UserRole.ADMIN).count();
    }

    public long getAlumniRoleCount() {
        return userRepository.findAll().stream().filter(u -> u.getRole() == UserRole.ALUMNI).count();
    }

    public long getStudentRoleCount() {
        return userRepository.findAll().stream().filter(u -> u.getRole() == UserRole.STUDENT).count();
    }

    public List<PlacementStat> getAllPlacementStats() {
        return placementStatRepository.findAllByOrderByYearDesc();
    }
}