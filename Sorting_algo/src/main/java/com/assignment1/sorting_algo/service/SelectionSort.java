package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectionSort {
    public List<Step> sort(RequestDTO request) { // Return the list of steps
        Helper help = new Helper();
        List<Integer> array = help.generator(request);

        int stepNum = 0;
        int comparisons = 0;
        int interchanges = 0;
        List<Step> steps = new ArrayList<>();

        int n = array.size();

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                steps.add(Step.builder()
                        .operation("comparison")
                        .step_num(++stepNum)
                        .array(new ArrayList<>(array))
                        .ptr1(j)
                        .ptr2(min_idx)
                        .totalComparisons(comparisons)
                        .totalInterchanges(interchanges)
                        .build());
                if (array.get(min_idx) > array.get(j)) {
                    min_idx = j;
                }
            }
            if (min_idx != i) {
                interchanges++;
                help.swap(array,min_idx,i);
                steps.add(Step.builder()
                        .operation("swap")
                        .step_num(++stepNum)
                        .array(new ArrayList<>(array))
                        .ptr1(min_idx)
                        .ptr2(i)
                        .totalComparisons(comparisons)
                        .totalInterchanges(interchanges)
                        .build());
            }
        }
        return steps;
    }
}
