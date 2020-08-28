package com.sparta.sorters;

import com.sparta.SortResults;
import java.util.Arrays;

public class InsertionSorter implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        long startTime, sortTime;
        int numberToInsert, insertIndex;
        double sortTimeMilliSeconds;

        startTime = System.nanoTime();

        for (int index = 1; index < sortedArray.length; index++) {
            // current
            numberToInsert = unsortedArray[index];
            // go backwards through list until a larger element is found
            for (insertIndex = index - 1; insertIndex >= 0 && sortedArray[insertIndex] > numberToInsert; insertIndex--) {
                sortedArray[insertIndex + 1] = sortedArray[insertIndex];
            }
            sortedArray[insertIndex + 1] = numberToInsert;
        }

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Insertion Sort", sortedArray, sortTimeMilliSeconds);
    }
}
