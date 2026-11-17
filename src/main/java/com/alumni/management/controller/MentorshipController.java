package com.alumni.management.controller;

import com.alumni.management.entity.MentorshipRequest;
import com.alumni.management.entity.StudentProfile;
import com.alumni.management.entity.User;
import com.alumni.management.service.MentorshipService;
import com.alumni.management.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mentorship")
public class MentorshipController {

    private final MentorshipService mentorshipService;
    private final StudentService studentService;

    public MentorshipController(MentorshipService mentorshipService, StudentService studentService) {
        this.mentorshipService = mentorshipService;
        this.studentService = studentService;
    }

    @GetMapping
    public String viewMentorship(Model model) {
        model.addAttribute("requests", mentorshipService.getAllRequests());
        model.addAttribute("students", mentorshipService.getAllStudents());
        model.addAttribute("alumniList", mentorshipService.getAllAlumni());
        model.addAttribute("newStudent", new StudentProfile());
        return "mentorship";
    }

    @PostMapping("/student/register")
    public String registerStudent(@RequestParam String name,
                                  @RequestParam String email,
                                  @ModelAttribute StudentProfile profile) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        studentService.saveStudentProfile(profile, user);
        return "redirect:/mentorship";
    }

    @PostMapping("/request")
    public String createRequest(@RequestParam Long studentId,
                                @RequestParam Long alumniId,
                                @RequestParam String message) {
        mentorshipService.createRequest(studentId, alumniId, message);
        return "redirect:/mentorship";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam MentorshipRequest.Status status) {
        mentorshipService.updateStatus(id, status);
        return "redirect:/mentorship";
    }
}