package com.dashboard.productivity.repository;

import com.dashboard.productivity.dto.DeveloperSummaryDTO;
import com.dashboard.productivity.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {

    @Query("""
            SELECT new com.dashboard.productivity.dto.DeveloperSummaryDTO(
                d.id, d.name, d.team,
                SUM(m.commits), SUM(m.tasksCompleted), SUM(m.incidentsResolved),
                SUM(m.linesAdded), SUM(m.linesRemoved)
            )
            FROM Metric m JOIN m.developer d
            GROUP BY d.id, d.name, d.team
            ORDER BY SUM(m.commits) DESC
            """)
    List<DeveloperSummaryDTO> findDeveloperSummaries();

    List<Metric> findByDeveloperId(Long developerId);

    @Query("SELECT SUM(m.commits) FROM Metric m")
    Long sumCommits();

    @Query("SELECT SUM(m.tasksCompleted) FROM Metric m")
    Long sumTasks();

    @Query("SELECT SUM(m.incidentsResolved) FROM Metric m")
    Long sumIncidents();

    @Query("SELECT SUM(m.linesAdded) FROM Metric m")
    Long sumLinesAdded();
}
