package com.dashboard.productivity.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "metrics")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;

    private LocalDate date;
    private Integer commits;
    private Integer tasksCompleted;
    private Integer incidentsResolved;
    private Integer linesAdded;
    private Integer linesRemoved;

    public Metric() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Developer getDeveloper() { return developer; }
    public void setDeveloper(Developer developer) { this.developer = developer; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Integer getCommits() { return commits; }
    public void setCommits(Integer commits) { this.commits = commits; }
    public Integer getTasksCompleted() { return tasksCompleted; }
    public void setTasksCompleted(Integer tasksCompleted) { this.tasksCompleted = tasksCompleted; }
    public Integer getIncidentsResolved() { return incidentsResolved; }
    public void setIncidentsResolved(Integer incidentsResolved) { this.incidentsResolved = incidentsResolved; }
    public Integer getLinesAdded() { return linesAdded; }
    public void setLinesAdded(Integer linesAdded) { this.linesAdded = linesAdded; }
    public Integer getLinesRemoved() { return linesRemoved; }
    public void setLinesRemoved(Integer linesRemoved) { this.linesRemoved = linesRemoved; }
}
