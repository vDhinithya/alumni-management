package com.alumni.management.controller;

import com.alumni.management.entity.AlumniProfile;
import com.alumni.management.entity.StudentProfile;
import com.alumni.management.entity.User;
import com.alumni.management.entity.UserRole;
import com.alumni.management.repository.AlumniProfileRepository;
import com.alumni.management.repository.StudentProfileRepository;
import com.alumni.management.repository.UserRepository;
import com.alumni.management.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    private final UserRepository userRepository;
    private final AlumniProfileRepository alumniProfileRepository;
    private final StudentProfileRepository studentProfileRepository;

    public DashboardController(DashboardService dashboardService,
                               UserRepository userRepository,
                               AlumniProfileRepository alumniProfileRepository,
                               StudentProfileRepository studentProfileRepository) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
        this.alumniProfileRepository = alumniProfileRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("alumniCount", dashboardService.getRegisteredAlumniCount());
        model.addAttribute("employmentCount", dashboardService.getEmploymentRecordsCount());
        model.addAttribute("mentorshipCount", dashboardService.getMentorshipRequestsCount());
        model.addAttribute("placementCount", dashboardService.getPlacementRecordsCount());

        model.addAttribute("adminCount", dashboardService.getAdminCount());
        model.addAttribute("alumniRoleCount", dashboardService.getAlumniRoleCount());
        model.addAttribute("studentRoleCount", dashboardService.getStudentRoleCount());
        model.addAttribute("placementStats", dashboardService.getAllPlacementStats());

        return "dashboard";
    }

    // Unified quick registration from Dashboard
    @PostMapping("/register-user")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam UserRole role,
                               @RequestParam(required = false) Integer graduationYear,
                               @RequestParam(required = false) String department,
                               @RequestParam(required = false) String degree,
                               @RequestParam(required = false) String currentCompany,
                               @RequestParam(required = false) String designation,
                               @RequestParam(required = false) String location,
                               @RequestParam(required = false) String bio,
                               @RequestParam(required = false) Integer enrollmentYear,
                               @RequestParam(required = false) Integer currentSemester) {

        if (userRepository.existsByEmail(email)) {
            return "redirect:/?error=email_exists";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setApproved(true);
        user.setHashedPassword("demo_secure_pass");
        User savedUser = userRepository.save(user);

        if (role == UserRole.ALUMNI) {
            AlumniProfile profile = new AlumniProfile();
            profile.setUser(savedUser);
            profile.setGraduationYear(graduationYear != null ? graduationYear : 2023);
            profile.setDepartment(department != null ? department : "Computer Science");
            profile.setDegree(degree != null ? degree : "B.Tech");
            profile.setCurrentCompany(currentCompany);
            profile.setDesignation(designation);
            profile.setLocation(location);
            profile.setBio(bio);
            alumniProfileRepository.save(profile);
        } else if (role == UserRole.STUDENT) {
            StudentProfile profile = new StudentProfile();
            profile.setUser(savedUser);
            profile.setEnrollmentYear(enrollmentYear != null ? enrollmentYear : 2024);
            profile.setDepartment(department != null ? department : "Computer Science");
            profile.setCurrentSemester(currentSemester != null ? currentSemester : 4);
            studentProfileRepository.save(profile);
        }

        return "redirect:/";
    }
}