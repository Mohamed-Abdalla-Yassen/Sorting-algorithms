package com.assignment1.sorting_algo.service_comparator;

import com.assignment1.sorting_algo.DTO.DataOfMethod;
import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.UnitRun;
import com.assignment1.sorting_algo.service.Helper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private static int comparisons;
    private static int swaps;
    private Helper help = new Helper();

    private UnitRun sort(List<Integer> array) {

        comparisons = 0;
        swaps = 0;
        UnitRun data = new UnitRun();

        int n = array.size();
        long startTime = System.nanoTime(); // start of timer
        buildMaxHeap(array);

        for (int i = n - 1; i > 0; i--) {
            help.swap(array, 0, i);
            swaps++;
            heapify( 0, i,array);
        }

        long endTime = System.nanoTime(); // end of timer
        long durationInNano = (endTime - startTime);

        data.setComparisons(comparisons);
        data.setSwaps(swaps);
        data.setRunTime(durationInNano);

        return data;
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
            max_idx = l_child;
        }
        if (r_child < size && array.get(max_idx) < array.get(r_child)) {
            comparisons++;
            max_idx = r_child;
        }
        if (max_idx != node_idx) {
            help.swap(array,max_idx,node_idx);
            swaps++;
            heapify(max_idx,size,array);
        }
    }

    public DataOfMethod multipleRuns(int runs , List<Integer> array) throws IOException {
        String csvFilePath = "benchmark_results_haep.csv";
        PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath)); // test

        Helper help = new Helper();
        DataOfMethod d = new DataOfMethod();

        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        long sumOfTime = 0;
        long avgTime = 0;

        UnitRun run = new UnitRun();

        writer.println("Run_Number,Execution_Time_ns");
        for (int i = 0; i < runs; i++) {
            run = sort(new ArrayList<>(array));

            writer.println(i+1 + "," + run.getRunTime()); // test

            if (minTime > run.getRunTime()) minTime  = run.getRunTime();
            if (maxTime < run.getRunTime()) maxTime  = run.getRunTime();
            sumOfTime += run.getRunTime();
        }

        avgTime = (long)(sumOfTime / runs);

        d.setSwaps(run.getSwaps());
        d.setComparisons(run.getComparisons());
        d.setMinTime(minTime);
        d.setMaxTime(maxTime);
        d.setAvgTime(avgTime);

        writer.close();
        return d;
    }
}
