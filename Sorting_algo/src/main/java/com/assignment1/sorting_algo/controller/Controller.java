package com.assignment1.sorting_algo.controller;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import com.assignment1.sorting_algo.service.InsertionSort;
import com.assignment1.sorting_algo.service.SelectionSort;
import org.springframework.web.bind.annotation.*;
import com.assignment1.sorting_algo.service.BubbleSort;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    private final BubbleSort bubble_service;
    private final SelectionSort selection_service;
    private final InsertionSort insertion_service;

    // Constructor injection
    public Controller(BubbleSort bubble_service,SelectionSort selection_service,InsertionSort insertion_service) {
        this.bubble_service = bubble_service;
        this.selection_service = selection_service;
        this.insertion_service = insertion_service;
    }

    @PostMapping("/bubble_sort")
    public List<Step> bubble_sort(@RequestBody RequestDTO request) {
        return bubble_service.sort(request);
    }

    @PostMapping("/selection_sort")
    public List<Step> selection_sort(@RequestBody RequestDTO request) {
        return selection_service.sort(request);
    }

    @PostMapping("/insertion_sort")
    public List<Step> insertion_sort(@RequestBody RequestDTO request) {
        return insertion_service.sort(request);
    }
}
