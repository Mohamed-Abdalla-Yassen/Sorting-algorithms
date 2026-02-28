package com.assignment1.sorting_algo.service;

import com.assignment1.sorting_algo.DTO.RequestDTO;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public void swap(List<Integer> array, int idx1, int idx2) {
        int temp = array.get(idx1);
        array.set(idx1, array.get(idx2));
        array.set(idx2, temp);
    }

    public List<Integer> generator(RequestDTO request) {
        int size = request.getSize();
        int order = request.getOrder();
        List<Integer> array = new ArrayList<Integer>();
        if(order == 3) { // unsorted
            for (int i = 0; i < size; i++) {
                array.add((int)(Math.random() * 400));
            }
        } else if (order == 2) {
            for (int i = 0; i < size; i++) {
                array.add(size - i);
            }
        } else if (order == 1) {
            for (int i = 0; i < size; i++) {
                array.add(i+1);
            }
        }
        return array;
    }
}
