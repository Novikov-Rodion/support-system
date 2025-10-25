package com.example.support_system.model;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private String title;
    private String description;
    private User createdBy;
    private Category category;
    private Agent assignedAgent;
    private TicketStatus status = TicketStatus.NEW;
    private String solution;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum TicketStatus {
        NEW, IN_PROGRESS, WAITING_RESPONSE, RESOLVED, CLOSED
    }

    public Ticket() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Ticket(String title, String description, User createdBy, Category category) {
        this();
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public Agent getAssignedAgent() { return assignedAgent; }
    public void setAssignedAgent(Agent assignedAgent) { this.assignedAgent = assignedAgent; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}