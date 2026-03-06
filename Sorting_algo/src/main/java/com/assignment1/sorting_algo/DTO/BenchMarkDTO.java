package com.assignment1.sorting_algo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BenchMarkDTO {
    private String algo_name;
    private String generation_mode; // how array is formed
    private DataOfMethod data;
    private int size; // size of array
    private int runs; // number of runs
}
