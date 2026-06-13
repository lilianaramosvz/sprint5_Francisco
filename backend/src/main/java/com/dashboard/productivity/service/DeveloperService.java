package com.dashboard.productivity.service;

import com.dashboard.productivity.model.Developer;
import com.dashboard.productivity.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Developer findById(Long id) {
        return developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desarrollador no encontrado con id: " + id));
    }
}
