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

public class Merge {
    private static int comparisons = 0;
    private static int swaps = 0;

    private UnitRun sort(List<Integer> array) { // Return the list of steps
        comparisons = 0;
        swaps = 0;

        UnitRun data = new UnitRun();

        int n = array.size();

        long startTime = System.nanoTime(); // start of timer

        // logic
        divide(array,0 , n-1);
        // end

        long endTime = System.nanoTime(); // end of timer
        long durationInNano = (endTime - startTime);

        data.setComparisons(comparisons);
        data.setSwaps(swaps);
        data.setRunTime(durationInNano);

        return data;
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
        }

        // Copy remaining elements of L[] and R[]
        while (i < L.size()) {
            array.set(k, L.get(i));
            i++; k++; swaps++;
        }
        while (j < R.size()) {
            array.set(k, R.get(j));
            j++; k++; swaps++;
        }
    }

    public DataOfMethod multipleRuns(int runs , List<Integer> array) throws IOException {
        String csvFilePath = "benchmark_results_merge.csv";
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
