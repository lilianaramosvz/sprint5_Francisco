package com.dashboard.productivity.dto;

public class OverviewDTO {

    private Long totalCommits;
    private Long totalTasks;
    private Long totalIncidents;
    private Long totalLinesAdded;
    private Long totalDevelopers;

    public OverviewDTO(Long totalCommits, Long totalTasks, Long totalIncidents,
                       Long totalLinesAdded, Long totalDevelopers) {
        this.totalCommits = totalCommits;
        this.totalTasks = totalTasks;
        this.totalIncidents = totalIncidents;
        this.totalLinesAdded = totalLinesAdded;
        this.totalDevelopers = totalDevelopers;
    }

    public Long getTotalCommits() { return totalCommits; }
    public Long getTotalTasks() { return totalTasks; }
    public Long getTotalIncidents() { return totalIncidents; }
    public Long getTotalLinesAdded() { return totalLinesAdded; }
    public Long getTotalDevelopers() { return totalDevelopers; }
}
