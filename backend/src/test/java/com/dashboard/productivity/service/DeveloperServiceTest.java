package com.dashboard.productivity.service;

import com.dashboard.productivity.model.Developer;
import com.dashboard.productivity.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperService developerService;

    @Test
    void findAll_returnsAllDevelopers() {
        Developer dev = new Developer("Ana García", "ana@dev.com", "Backend");
        when(developerRepository.findAll()).thenReturn(List.of(dev));

        List<Developer> result = developerService.findAll();

        assertEquals(1, result.size());
        assertEquals("Ana García", result.get(0).getName());
        verify(developerRepository, times(1)).findAll();
    }

    @Test
    void findById_existingId_returnsDeveloper() {
        Developer dev = new Developer("Ana García", "ana@dev.com", "Backend");
        dev.setId(1L);
        when(developerRepository.findById(1L)).thenReturn(Optional.of(dev));

        Developer result = developerService.findById(1L);

        assertEquals("Ana García", result.getName());
        assertEquals("Backend", result.getTeam());
    }

    @Test
    void findById_nonExistingId_throwsException() {
        when(developerRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> developerService.findById(99L));

        assertTrue(ex.getMessage().contains("99"));
    }
}
