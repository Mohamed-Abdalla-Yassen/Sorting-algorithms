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

public class Quick {
    private static int comparisons = 0;
    private static int swaps = 0;
    private Helper help = new Helper();

    private UnitRun sort(List<Integer> array){ // Return the list of steps
        comparisons = 0;
        swaps = 0;
        UnitRun data = new UnitRun();

        int n = array.size();

        long startTime = System.nanoTime(); // start of timer

        recursion(0 , n - 1 , array);

        long endTime = System.nanoTime(); // end of timer
        long durationInNano = (endTime - startTime);

        data.setComparisons(comparisons);
        data.setSwaps(swaps);
        data.setRunTime(durationInNano);

        return data;
    }

    public void recursion(int l,int r,List<Integer> array) {
        if (l >= r) return;

        int pivotVal = array.get(r);
        int i = l - 1;
        int j = l;

        for (;j < r ;){
            comparisons++;

            if (pivotVal > array.get(j)) {
                i++;
                swaps++;
                help.swap(array,j,i);

            }
            j++;
        }
        swaps++;
        help.swap(array,r,++i);

        int pivot_idx = i;
        recursion(l,pivot_idx-1,array);
        recursion(pivot_idx+1,r,array);
    }

    public DataOfMethod multipleRuns(int runs , List<Integer> array) throws IOException {
        String csvFilePath = "benchmark_results_quick.csv";
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
