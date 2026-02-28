package com.assignment1.sorting_algo.controller;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import com.assignment1.sorting_algo.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    private final BubbleSort bubble_service;
    private final SelectionSort selection_service;
    private final InsertionSort insertion_service;
    private final MergeSort merge_service;
    private final QuickSort quick_service;
    private final HeapSort heap_service;
    // Constructor injection
    public Controller(BubbleSort bubble_service,
                      SelectionSort selection_service,
                      InsertionSort insertion_service,
                      QuickSort quick_service,
                      HeapSort heap_service,
                      MergeSort merge_service) {
        this.bubble_service = bubble_service;
        this.selection_service = selection_service;
        this.insertion_service = insertion_service;
        this.quick_service = quick_service;
        this.heap_service = heap_service;
        this.merge_service = merge_service;
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

    @PostMapping("/quick_sort")
    public List<Step> quick_sort(@RequestBody RequestDTO request) {
        return quick_service.sort(request);
    }

    @PostMapping("/heap_sort")
    public List<Step> heap_sort(@RequestBody RequestDTO request) {
        return heap_service.sort(request);
    }

    @PostMapping("/merge_sort")
    public List<Step> merge_sort(@RequestBody RequestDTO request) {
        return merge_service.sort(request);
    }
}
