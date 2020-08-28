package com.sparta.sorters;

import com.sparta.sorters.auxiliaries.Merger;
import com.sparta.SortResults;
import java.util.Arrays;

public class MergeIterativeSorter extends Merger implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        int subArraySize;
        int start, middle, end, lastIndex = sortedArray.length - 1;

        startTime = System.nanoTime();

        for (subArraySize = 1; subArraySize <= lastIndex; subArraySize *= 2) {

            for (start = 0; start < lastIndex; start += (subArraySize * 2))
            {
                middle = Math.min(start + subArraySize - 1, lastIndex);
                end = Math.min(start + (subArraySize * 2) - 1, lastIndex);
                // merge both sub arrays [start...middle] and [middle+1...end]
                this.mergeSubArrays(sortedArray, start, middle, end);
            }
        }

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Iterative Merge Sort", sortedArray, sortTimeMilliSeconds);
    }
}
