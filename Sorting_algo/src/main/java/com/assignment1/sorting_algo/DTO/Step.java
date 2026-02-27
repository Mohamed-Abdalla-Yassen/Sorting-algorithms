package com.assignment1.sorting_algo.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Step {
    private int step_num;
    private int totalComparisons;
    private int totalInterchanges;
    private String operation; // swap or compare
    private List<Integer> array;
    private int ptr1; // determine compared or swapped items
    private int ptr2; //
//    private List<Integer> completedIdx;
}
