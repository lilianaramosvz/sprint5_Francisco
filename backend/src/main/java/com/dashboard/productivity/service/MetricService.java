package com.dashboard.productivity.service;

import com.dashboard.productivity.dto.DeveloperSummaryDTO;
import com.dashboard.productivity.dto.OverviewDTO;
import com.dashboard.productivity.model.Metric;
import com.dashboard.productivity.repository.DeveloperRepository;
import com.dashboard.productivity.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {

    private final MetricRepository metricRepository;
    private final DeveloperRepository developerRepository;

    public MetricService(MetricRepository metricRepository, DeveloperRepository developerRepository) {
        this.metricRepository = metricRepository;
        this.developerRepository = developerRepository;
    }

    public List<DeveloperSummaryDTO> getDeveloperSummaries() {
        return metricRepository.findDeveloperSummaries();
    }

    public List<Metric> getMetricsByDeveloper(Long developerId) {
        return metricRepository.findByDeveloperId(developerId);
    }

    public OverviewDTO getOverview() {
        Long totalCommits = metricRepository.sumCommits();
        Long totalTasks = metricRepository.sumTasks();
        Long totalIncidents = metricRepository.sumIncidents();
        Long totalLinesAdded = metricRepository.sumLinesAdded();
        Long totalDevelopers = developerRepository.count();
        return new OverviewDTO(totalCommits, totalTasks, totalIncidents, totalLinesAdded, totalDevelopers);
    }
}
