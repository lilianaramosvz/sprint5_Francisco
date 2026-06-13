package com.dashboard.productivity.controller;

import com.dashboard.productivity.dto.DeveloperSummaryDTO;
import com.dashboard.productivity.dto.OverviewDTO;
import com.dashboard.productivity.model.Metric;
import com.dashboard.productivity.service.MetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("/summary")
    public ResponseEntity<List<DeveloperSummaryDTO>> getSummary() {
        return ResponseEntity.ok(metricService.getDeveloperSummaries());
    }

    @GetMapping("/developer/{developerId}")
    public ResponseEntity<List<Metric>> getByDeveloper(@PathVariable Long developerId) {
        return ResponseEntity.ok(metricService.getMetricsByDeveloper(developerId));
    }

    @GetMapping("/overview")
    public ResponseEntity<OverviewDTO> getOverview() {
        return ResponseEntity.ok(metricService.getOverview());
    }
}
