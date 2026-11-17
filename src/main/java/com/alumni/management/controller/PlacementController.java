package com.alumni.management.controller;

import com.alumni.management.entity.PlacementStat;
import com.alumni.management.service.PlacementStatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/placements")
public class PlacementController {

    private final PlacementStatService placementStatService;

    public PlacementController(PlacementStatService placementStatService) {
        this.placementStatService = placementStatService;
    }

    @GetMapping
    public String viewPlacements(Model model) {
        model.addAttribute("placements", placementStatService.getAllStats());
        model.addAttribute("newPlacement", new PlacementStat());
        return "placements";
    }

    @PostMapping("/add")
    public String addPlacement(@ModelAttribute PlacementStat placementStat) {
        placementStatService.saveStat(placementStat);
        return "redirect:/placements";
    }
}