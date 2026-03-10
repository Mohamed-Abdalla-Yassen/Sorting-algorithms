package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsertionSort {
    public List<Step> sort(RequestDTO request) { // Return the list of steps
        Helper help = new Helper();
//        List<Integer> array = help.generator(request);
        List<Integer> array = new ArrayList<>();
        if (request.getOrder() == 4) {
            array = request.getArray();
        } else {
            array = help.generator(request);
        }
        int stepNum = 0;
        int comparisons = 0;
        int interchanges = 0;
        List<Step> steps = new ArrayList<>();

        int n = array.size();

        for (int i = 1; i < n ; i++) {
            for (int j = i; j > 0 ; j--) {
                comparisons++;
                steps.add(Step.builder()
                        .operation("comparison")
                        .step_num(++stepNum)
                        .array(new ArrayList<>(array))
                        .ptr1(j)
                        .ptr2(j - 1)
                        .totalComparisons(comparisons)
                        .totalInterchanges(interchanges)
                        .build());
                if (array.get(j) < array.get(j - 1)) {
                    interchanges++;
                    help.swap(array,j,j-1);
                    steps.add(Step.builder()
                            .operation("swap")
                            .step_num(++stepNum)
                            .array(new ArrayList<>(array))
                            .ptr1(j)
                            .ptr2(j - 1)
                            .totalComparisons(comparisons)
                            .totalInterchanges(interchanges)
                            .build());
                }
                else break;
            }
        }

        return steps;
    }
}
