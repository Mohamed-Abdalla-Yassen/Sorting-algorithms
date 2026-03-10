package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuickSort {
    private static int stepNum;
    private static int comparisons = 0;
    private static int swaps = 0;
    private Helper help = new Helper();
    private static List<Step> steps;

    public List<Step> sort(RequestDTO request) { // Return the list of steps
//        List<Integer> array = help.generator(request);
        List<Integer> array = new ArrayList<>();
        if (request.getOrder() == 4) {
            array = request.getArray();
        } else {
            array = help.generator(request);
        }
        steps = new ArrayList<>();
        stepNum = 0;
        comparisons = 0;
        swaps = 0;

        int n = array.size();

        recursion(0 , n - 1 , array);
        return steps;
    }

    public void recursion(int l,int r,List<Integer> array) {
        if (l >= r) return;

        int pivotVal = array.get(r);
        int i = l - 1;
        int j = l;

        for (;j < r ;){
            comparisons++;
            steps.add(Step.builder()
                    .operation("comparison")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(j)
                    .ptr2(r)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());
            if (pivotVal > array.get(j)) {
                i++;
                swaps++;
                help.swap(array,j,i);
                steps.add(Step.builder()
                        .operation("swap")
                        .step_num(++stepNum)
                        .array(new ArrayList<>(array))
                        .ptr1(j)
                        .ptr2(i)
                        .totalComparisons(comparisons)
                        .totalInterchanges(swaps)
                        .build());
            }
            j++;
        }
        swaps++;
        help.swap(array,r,++i);
        steps.add(Step.builder()
                .operation("swap")
                .step_num(++stepNum)
                .array(new ArrayList<>(array))
                .ptr1(r)
                .ptr2(i)
                .totalComparisons(comparisons)
                .totalInterchanges(swaps)
                .build());
        int pivot_idx = i;
        recursion(l,pivot_idx-1,array);
        recursion(pivot_idx+1,r,array);
    }
}
