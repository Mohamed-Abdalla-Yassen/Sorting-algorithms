package com.assignment1.sorting_algo.controller;

import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.web.bind.annotation.*;
import com.assignment1.sorting_algo.service.BubbleSort;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    private final BubbleSort service;

    // ✅ Constructor injection
    public Controller(BubbleSort service) {
        this.service = service;
    }

    @PostMapping("/sort")
    public List<Step> sort(@RequestBody int size) {
        return service.sort(size);
    }
}
