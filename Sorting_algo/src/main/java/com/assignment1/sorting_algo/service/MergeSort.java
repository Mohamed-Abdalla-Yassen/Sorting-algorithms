package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MergeSort {
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

        divide(array,0 , n-1);
        return steps;
    }

    public void divide(List<Integer> array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            divide(array, left, mid);
            divide(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }
    private void merge(List<Integer> array, int left, int mid, int right) {
        // Create temporary lists for the two halves
        List<Integer> L = new ArrayList<>(array.subList(left, mid + 1));
        List<Integer> R = new ArrayList<>(array.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < L.size() && j < R.size()) {
            comparisons++;
            steps.add(Step.builder()
                    .operation("comparison")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(left + i)
                    .ptr2(mid + 1 + j)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());
            // Compare elements from the two temporary halves
            if (L.get(i) <= R.get(j)) {
                array.set(k, L.get(i));
                i++;
            } else {
                array.set(k, R.get(j));
                j++;
            }
            k++; // the index have been updated
            swaps++;
            steps.add(Step.builder()
                    .operation("swap")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(k)
                    .ptr2(k)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());
        }

        // Copy remaining elements of L[] and R[]
        while (i < L.size()) {
            array.set(k, L.get(i));
            i++; k++; swaps++;
            steps.add(Step.builder()
                    .operation("swap")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(k)
                    .ptr2(k)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());
        }
        while (j < R.size()) {
            array.set(k, R.get(j));
            j++; k++; swaps++;
            steps.add(Step.builder()
                    .operation("swap")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(k)
                    .ptr2(k)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());
        }
    }
}
