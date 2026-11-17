package com.alumni.management.service;

import com.alumni.management.entity.AlumniProfile;
import com.alumni.management.entity.User;
import com.alumni.management.entity.UserRole;
import com.alumni.management.repository.AlumniProfileRepository;
import com.alumni.management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumniService {

    private final AlumniProfileRepository alumniProfileRepository;
    private final UserRepository userRepository;

    public AlumniService(AlumniProfileRepository alumniProfileRepository, UserRepository userRepository) {
        this.alumniProfileRepository = alumniProfileRepository;
        this.userRepository = userRepository;
    }

    public List<AlumniProfile> getAllAlumni() {
        return alumniProfileRepository.findAll();
    }

    @Transactional
    public void saveAlumniProfile(AlumniProfile profile, User user) {
        user.setRole(UserRole.ALUMNI);
        user.setApproved(true);
        user.setHashedPassword("demo_secure_pass"); // Default password for demo

        User savedUser = userRepository.save(user);
        profile.setUser(savedUser);
        alumniProfileRepository.save(profile);
    }
}