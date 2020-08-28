package com.sparta.sorters;

import com.sparta.sorters.auxiliaries.Swapper;
import com.sparta.SortResults;
import java.util.Arrays;

public class SelectionSorter extends Swapper implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        int lastIndex = sortedArray.length - 1;
        int smallestElementIndex;

        startTime = System.nanoTime();

        for (int start = 0; start < lastIndex; start++) {

            smallestElementIndex = getIndexOfSmallestElement(sortedArray, start, lastIndex);

            // put smallest element in order
            swap(sortedArray, start, smallestElementIndex);
        }

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Selection Sort", sortedArray, sortTimeMilliSeconds);
    }

    public int getIndexOfSmallestElement(int[] array, int start, int end) {
        int smallestElementIndex = start;

        for (int index = start + 1; index <= end; index++) {
            // if current element is smaller, change smallest element index to current index
            if (array[index] < array[smallestElementIndex]) {
                smallestElementIndex = index;
            }
        }

        return smallestElementIndex;
    }
}
