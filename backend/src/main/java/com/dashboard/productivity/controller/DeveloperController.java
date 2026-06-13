package com.dashboard.productivity.controller;

import com.dashboard.productivity.model.Developer;
import com.dashboard.productivity.service.DeveloperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<List<Developer>> getAll() {
        return ResponseEntity.ok(developerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.findById(id));
    }
}
