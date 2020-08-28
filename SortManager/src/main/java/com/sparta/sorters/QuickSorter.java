package com.sparta.sorters;

import com.sparta.sorters.auxiliaries.Swapper;
import com.sparta.SortResults;
import java.util.Arrays;

public class QuickSorter extends Swapper implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        startTime = System.nanoTime();

        sortPartitions(sortedArray, 0, sortedArray.length - 1);

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Quick Sort", sortedArray, sortTimeMilliSeconds);
    }

    public void sortPartitions(int[] array, int start, int end) {
        int pivot;

        if ((end - start) > 0) {
            pivot = getPivotIndex(array, start, end);

            sortPartitions(array, start, pivot - 1);
            sortPartitions(array, pivot + 1, end);
        }
    }

    public int getPivotIndex(int[] array, int start, int end) {
        int pivot = array[end];                     // last element becomes pivot
        int lastSmallerElementIndex = (start - 1);  // index of the last smaller element before the pivot

        for (int index = start; index <= end; index++) {
            // If current element is smaller than the pivot
            if (array[index] < pivot)  {
                lastSmallerElementIndex++;
                swap(array, index, lastSmallerElementIndex);
            }
        }
        // finally, swap pivot and the element after the last element smaller than the pivot
        swap(array, end, lastSmallerElementIndex + 1);

        return lastSmallerElementIndex + 1;
    }


}
