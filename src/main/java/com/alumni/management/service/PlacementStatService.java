package com.alumni.management.service;

import com.alumni.management.entity.PlacementStat;
import com.alumni.management.repository.PlacementStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementStatService {

    private final PlacementStatRepository placementStatRepository;

    public PlacementStatService(PlacementStatRepository placementStatRepository) {
        this.placementStatRepository = placementStatRepository;
    }

    public List<PlacementStat> getAllStats() {
        return placementStatRepository.findAllByOrderByYearDesc();
    }

    public void saveStat(PlacementStat stat) {
        placementStatRepository.save(stat);
    }
}