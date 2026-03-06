package com.assignment1.sorting_algo.service_comparator;

import com.assignment1.sorting_algo.DTO.DataOfMethod;
import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.DTO.UnitRun;
import com.assignment1.sorting_algo.service.Helper;

import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Bubble {
    private UnitRun sort(List<Integer> array) { // para --> array

        Helper help = new Helper();

        int comparisons = 0;
        int interchanges = 0;
        UnitRun data = new UnitRun();

        int n = array.size();

        long startTime = System.nanoTime(); // start of timer

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;

                if (array.get(j) > array.get(j + 1)) {
                    interchanges++;
                    help.swap(array, j, j + 1);
                    swapped = true;

                }
            }
            if (!swapped) break;
        }

        long endTime = System.nanoTime(); // end of timer
        long durationInNano = (endTime - startTime);
        // To convert to milliseconds:
//        long durationInMs = (long) (durationInNano / 1_000_000.0);

        data.setComparisons(comparisons);
        data.setSwaps(interchanges);
        data.setRunTime(durationInNano);

        return data;
    }

    public DataOfMethod multipleRuns(int runs , List<Integer> array) throws IOException {
        String csvFilePath = "benchmark_results.csv";
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
