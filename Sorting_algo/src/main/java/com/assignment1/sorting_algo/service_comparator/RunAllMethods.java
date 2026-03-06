package com.assignment1.sorting_algo.service_comparator;

import com.assignment1.sorting_algo.DTO.BenchMarkDTO;
import com.assignment1.sorting_algo.DTO.DataOfMethod;
import com.assignment1.sorting_algo.DTO.RequestDTO;
import com.assignment1.sorting_algo.service.Helper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RunAllMethods {
    public List<BenchMarkDTO> runAll(RequestDTO r) throws IOException {
        List<BenchMarkDTO> bench = new ArrayList<>();
        DataOfMethod temp = new DataOfMethod();

        Helper help = new Helper();
        List<Integer> array = help.generator(r);

        // store benchmark data of bubble
        Bubble b = new Bubble();
        temp = b.multipleRuns(r.getRuns(),array);

        bench.add(BenchMarkDTO.builder()
                        .algo_name("Bubble Sort")
                        .generation_mode("test")
                        .size(r.getSize())
                        .runs(r.getRuns())
                        .data(temp)
                        .build());


        // store benchmark data of Insertion
        Insertion i = new Insertion();
        temp = i.multipleRuns(r.getRuns(),array);

        bench.add(BenchMarkDTO.builder()
                .algo_name("Insertion Sort")
                .generation_mode("test")
                .size(r.getSize())
                .runs(r.getRuns())
                .data(temp)
                .build());


        // store benchmark data of Selection
        Selection s = new Selection();
        temp = s.multipleRuns(r.getRuns(),array);

        bench.add(BenchMarkDTO.builder()
                .algo_name("Selection Sort")
                .generation_mode("test")
                .size(r.getSize())
                .runs(r.getRuns())
                .data(temp)
                .build());


        // store benchmark data of Merge
        Merge m = new Merge();
        temp = m.multipleRuns(r.getRuns(),array);

        bench.add(BenchMarkDTO.builder()
                .algo_name("Merge Sort")
                .generation_mode("test")
                .size(r.getSize())
                .runs(r.getRuns())
                .data(temp)
                .build());


        // store benchmark data of Quick
        Quick q = new Quick();
        temp = q.multipleRuns(r.getRuns(),array);

        bench.add(BenchMarkDTO.builder()
                .algo_name("Quick Sort")
                .generation_mode("test")
                .size(r.getSize())
                .runs(r.getRuns())
                .data(temp)
                .build());


        // store benchmark data of Heap
        Heap h = new Heap();
        temp = h.multipleRuns(r.getRuns(),array);

        bench.add(BenchMarkDTO.builder()
                .algo_name("Heap Sort")
                .generation_mode("test")
                .size(r.getSize())
                .runs(r.getRuns())
                .data(temp)
                .build());

        return bench;
    }
}
