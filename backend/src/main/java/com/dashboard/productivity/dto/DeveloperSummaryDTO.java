package com.dashboard.productivity.dto;

public class DeveloperSummaryDTO {

    private Long developerId;
    private String name;
    private String team;
    private Long totalCommits;
    private Long totalTasks;
    private Long totalIncidents;
    private Long totalLinesAdded;
    private Long totalLinesRemoved;

    public DeveloperSummaryDTO(Long developerId, String name, String team,
                                Long totalCommits, Long totalTasks, Long totalIncidents,
                                Long totalLinesAdded, Long totalLinesRemoved) {
        this.developerId = developerId;
        this.name = name;
        this.team = team;
        this.totalCommits = totalCommits;
        this.totalTasks = totalTasks;
        this.totalIncidents = totalIncidents;
        this.totalLinesAdded = totalLinesAdded;
        this.totalLinesRemoved = totalLinesRemoved;
    }

    public Long getDeveloperId() { return developerId; }
    public String getName() { return name; }
    public String getTeam() { return team; }
    public Long getTotalCommits() { return totalCommits; }
    public Long getTotalTasks() { return totalTasks; }
    public Long getTotalIncidents() { return totalIncidents; }
    public Long getTotalLinesAdded() { return totalLinesAdded; }
    public Long getTotalLinesRemoved() { return totalLinesRemoved; }
}
