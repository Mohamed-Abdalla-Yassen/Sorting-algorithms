package com.assignment1.sorting_algo.DTO;

import lombok.Data;

@Data
public class CompareDTO {
    private int size;
    private int runs;
    private String input; // "sorted"
                          // "random"
                          // "rev-sorted"
                          // "file.txt"
}
