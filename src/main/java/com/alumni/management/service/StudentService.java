package com.alumni.management.service;

import com.alumni.management.entity.StudentProfile;
import com.alumni.management.entity.User;
import com.alumni.management.entity.UserRole;
import com.alumni.management.repository.StudentProfileRepository;
import com.alumni.management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public StudentService(StudentProfileRepository studentProfileRepository, UserRepository userRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Transactional
    public void saveStudentProfile(StudentProfile profile, User user) {
        user.setRole(UserRole.STUDENT);
        user.setApproved(true);
        user.setHashedPassword("demo_secure_pass");
        User savedUser = userRepository.save(user);
        profile.setUser(savedUser);
        studentProfileRepository.save(profile);
    }
}