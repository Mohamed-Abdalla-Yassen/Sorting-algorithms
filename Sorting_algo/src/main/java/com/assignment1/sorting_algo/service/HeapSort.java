package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeapSort {

    private static int stepNum;
    private static int comparisons;
    private static int swaps;
    private Helper help = new Helper();
    private static List<Step> steps = new ArrayList<>();

    public List<Step> sort(RequestDTO request) {
        List<Integer> array = help.generator(request);

        stepNum = 0;
        comparisons = 0;
        swaps = 0;
        int n = array.size();
        buildMaxHeap(array);

        for (int i = n - 1; i > 0; i--) {
            help.swap(array, 0, i);

            swaps++;
            steps.add(Step.builder()
                    .operation("swap")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(0)
                    .ptr2(i)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());

            heapify( 0, i,array);
        }

        return steps;
    }

    private void buildMaxHeap(List<Integer> array) {
        for (int i = (array.size() / 2) - 1; i >= 0 ; i--) {
            heapify(i,array.size(), array);
        }
    }

    private void heapify(int node_idx,int size, List<Integer> array){
        int max_idx = node_idx; //patent_idx
        int l_child = node_idx * 2 + 1;
        int r_child = node_idx * 2 + 2;

        if (l_child < size && array.get(max_idx) < array.get(l_child)){
            comparisons++;
            steps.add(Step.builder()
                    .operation("comparison")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(max_idx)
                    .ptr2(l_child)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());

            max_idx = l_child;
        }
        if (r_child < size && array.get(max_idx) < array.get(r_child)) {
            comparisons++;
            steps.add(Step.builder()
                    .operation("comparison")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(max_idx)
                    .ptr2(r_child)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());

            max_idx = r_child;
        }
        if (max_idx != node_idx) {

            help.swap(array,max_idx,node_idx);
            swaps++;
            steps.add(Step.builder()
                    .operation("swap")
                    .step_num(++stepNum)
                    .array(new ArrayList<>(array))
                    .ptr1(max_idx)
                    .ptr2(node_idx)
                    .totalComparisons(comparisons)
                    .totalInterchanges(swaps)
                    .build());
            heapify(max_idx,size,array);
        }
    }
}
