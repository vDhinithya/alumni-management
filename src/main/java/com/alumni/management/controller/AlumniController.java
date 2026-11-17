package com.alumni.management.controller;

import com.alumni.management.entity.AlumniProfile;
import com.alumni.management.entity.User;
import com.alumni.management.service.AlumniService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumni")
public class AlumniController {

    private final AlumniService alumniService;

    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    @GetMapping
    public String viewAlumniDirectory(Model model) {
        model.addAttribute("alumniList", alumniService.getAllAlumni());
        model.addAttribute("newProfile", new AlumniProfile());
        return "alumni";
    }

    @PostMapping("/register")
    public String registerAlumni(@RequestParam String name,
                                 @RequestParam String email,
                                 @ModelAttribute AlumniProfile profile) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        alumniService.saveAlumniProfile(profile, user);
        return "redirect:/alumni";
    }
}