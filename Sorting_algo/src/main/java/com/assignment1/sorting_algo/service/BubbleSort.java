package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// in-place algo
@Service
public class BubbleSort {
    public List<Step> sort(int size) { // Return the list of steps

        List<Integer> array = generator(size);

        int stepNum = 0;
        int comparisons = 0;
        int interchanges = 0;
        List<Step> steps = new ArrayList<>();

        int n = array.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;

                // Record Comparison
                steps.add(Step.builder()
                        .operation("comparison")
                        .step_num(++stepNum)
                        .array(new ArrayList<>(array))
                        .ptr1(j)
                        .ptr2(j + 1)
                        .totalComparisons(comparisons)
                        .totalInterchanges(interchanges)
                        .build());

                if (array.get(j) > array.get(j + 1)) {
                    interchanges++;
                    swap(array, j, j + 1);
                    swapped = true;

                    // Record Swap
                    steps.add(Step.builder()
                            .operation("swap")
                            .step_num(++stepNum)
                            .array(new ArrayList<>(array))
                            .ptr1(j)
                            .ptr2(j + 1)
                            .totalComparisons(comparisons)
                            .totalInterchanges(interchanges)
                            .build());
                }
            }
            if (!swapped) break;
        }
        return steps;
    }

    private void swap(List<Integer> array, int idx1, int idx2) {
        int temp = array.get(idx1);
        array.set(idx1, array.get(idx2));
        array.set(idx2, temp);
    }

    private List<Integer> generator(int size) {
        List<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            array.add((int)(Math.random() * 400));
        }
        return array;
    }
}
