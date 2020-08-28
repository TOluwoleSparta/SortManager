package com.sparta.sorters;

import com.sparta.sorters.auxiliaries.Merger;
import com.sparta.SortResults;
import java.util.Arrays;

public class MergeSorter extends Merger implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        startTime = System.nanoTime();

        sortSubArrays(sortedArray, 0, sortedArray.length - 1);

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Merge Sort", sortedArray, sortTimeMilliSeconds);
    }

    public void sortSubArrays(int[] array, int start, int end) {
        int middle = (start + end) / 2;

        if ((end - start) > 0) {
            sortSubArrays(array, start, middle);           // left half
            sortSubArrays(array, middle + 1, end);    // right half

            this.mergeSubArrays(array, start, middle, end);
        }
    }
}
