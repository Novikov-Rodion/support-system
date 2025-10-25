package com.example.support_system.model;

import java.time.Duration;

public class SLA {
    private Long id;
    private String name;
    private Category category;
    private Duration responseTime;
    private Duration resolutionTime;

    public SLA() {}

    public SLA(String name, Category category, Duration responseTime, Duration resolutionTime) {
        this.name = name;
        this.category = category;
        this.responseTime = responseTime;
        this.resolutionTime = resolutionTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public Duration getResponseTime() { return responseTime; }
    public void setResponseTime(Duration responseTime) { this.responseTime = responseTime; }
    public Duration getResolutionTime() { return resolutionTime; }
    public void setResolutionTime(Duration resolutionTime) { this.resolutionTime = resolutionTime; }
}