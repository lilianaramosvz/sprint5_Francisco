package com.dashboard.productivity.service;

import com.dashboard.productivity.dto.DeveloperSummaryDTO;
import com.dashboard.productivity.dto.OverviewDTO;
import com.dashboard.productivity.repository.DeveloperRepository;
import com.dashboard.productivity.repository.MetricRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetricServiceTest {

    @Mock
    private MetricRepository metricRepository;

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private MetricService metricService;

    @Test
    void getOverview_returnsCorrectTotals() {
        when(metricRepository.sumCommits()).thenReturn(159L);
        when(metricRepository.sumTasks()).thenReturn(82L);
        when(metricRepository.sumIncidents()).thenReturn(52L);
        when(metricRepository.sumLinesAdded()).thenReturn(5120L);
        when(developerRepository.count()).thenReturn(5L);

        OverviewDTO result = metricService.getOverview();

        assertEquals(159L, result.getTotalCommits());
        assertEquals(82L, result.getTotalTasks());
        assertEquals(52L, result.getTotalIncidents());
        assertEquals(5120L, result.getTotalLinesAdded());
        assertEquals(5L, result.getTotalDevelopers());
    }

    @Test
    void getDeveloperSummaries_returnsListFromRepository() {
        DeveloperSummaryDTO dto = new DeveloperSummaryDTO(
                1L, "Ana García", "Backend", 37L, 18L, 9L, 1450L, 390L);
        when(metricRepository.findDeveloperSummaries()).thenReturn(List.of(dto));

        List<DeveloperSummaryDTO> result = metricService.getDeveloperSummaries();

        assertEquals(1, result.size());
        assertEquals("Ana García", result.get(0).getName());
        assertEquals(37L, result.get(0).getTotalCommits());
        verify(metricRepository, times(1)).findDeveloperSummaries();
    }

    @Test
    void getDeveloperSummaries_emptyRepository_returnsEmptyList() {
        when(metricRepository.findDeveloperSummaries()).thenReturn(List.of());

        List<DeveloperSummaryDTO> result = metricService.getDeveloperSummaries();

        assertTrue(result.isEmpty());
    }
}
