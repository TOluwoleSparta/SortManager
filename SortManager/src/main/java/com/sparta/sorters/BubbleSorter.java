package com.sparta.sorters;

import com.sparta.sorters.auxiliaries.Swapper;
import com.sparta.SortResults;
import java.util.Arrays;

public class BubbleSorter extends Swapper implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        int passes = 0, swapsMadeDuringThisPass;

        startTime = System.nanoTime();

        do {
            swapsMadeDuringThisPass = 0;
            // iterate through list, reduced for each pass that has been completed
            // (because the last element is always sorted after each pass)
            for (int index = 0; index < (sortedArray.length - passes - 1); index++) {
                if (sortedArray[index] > sortedArray[index + 1]) {
                    swap(sortedArray, index, index + 1);
                    swapsMadeDuringThisPass++;
                }
            }
            passes++;
        } while (swapsMadeDuringThisPass > 0);

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Bubble Sort", sortedArray, sortTimeMilliSeconds);
    }
}
