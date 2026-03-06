package com.assignment1.sorting_algo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RequestDTO {
    private int size;
    private int order; // 1 --> sorted
                       // 2 --> rev-sorted
                       // 3 --> random
                       // 4 --> file
    private int runs;
    private List<Integer> array;
}
