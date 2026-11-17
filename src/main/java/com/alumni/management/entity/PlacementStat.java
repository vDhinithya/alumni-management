package com.alumni.management.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "placement_stats")
public class PlacementStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(name = "total_graduates", nullable = false)
    private Integer totalGraduates;

    @Column(name = "total_placed", nullable = false)
    private Integer totalPlaced;

    @Column(name = "average_package", precision = 10, scale = 2)
    private BigDecimal averagePackage;

    @Column(name = "highest_package", precision = 10, scale = 2)
    private BigDecimal highestPackage;

    public PlacementStat() {
    }

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getTotalGraduates() {
        return totalGraduates;
    }

    public void setTotalGraduates(Integer totalGraduates) {
        this.totalGraduates = totalGraduates;
    }

    public Integer getTotalPlaced() {
        return totalPlaced;
    }

    public void setTotalPlaced(Integer totalPlaced) {
        this.totalPlaced = totalPlaced;
    }

    public BigDecimal getAveragePackage() {
        return averagePackage;
    }

    public void setAveragePackage(BigDecimal averagePackage) {
        this.averagePackage = averagePackage;
    }

    public BigDecimal getHighestPackage() {
        return highestPackage;
    }

    public void setHighestPackage(BigDecimal highestPackage) {
        this.highestPackage = highestPackage;
    }

    public double getPlacementPercentage() {
        if (totalGraduates == null || totalGraduates == 0) return 0.0;
        return (double) totalPlaced / totalGraduates * 100.0;
    }
}