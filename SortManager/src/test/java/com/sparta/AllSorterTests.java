package com.sparta;

import static org.junit.Assert.assertArrayEquals;
import com.sparta.sorters.*;
import org.junit.Test;

import java.util.Arrays;

public class AllSorterTests {
    private final int[] numberArray = new int[1_000];

    Sorter binarySorter = new BinarySorter();
    Sorter bubbleSorter = new BubbleSorter();
    Sorter insertionSorter = new InsertionSorter();
    Sorter mergeSorter = new MergeSorter();
    Sorter mergeIterativeSorter = new MergeIterativeSorter();
    Sorter quickSorter = new QuickSorter();
    Sorter selectionSorter = new SelectionSorter();
    Sorter treeBinarySorter = new TreeInterfaceBinarySorter();

    @Test
    public void testAllSorterSorts() {
        int[] correctArray;

        for (int index = 0; index < numberArray.length; index++) {
            numberArray[index] = (int) Math.round(Math.random() * 1000);
        }

        correctArray = Arrays.copyOf(numberArray, numberArray.length);

        // sort using in-built method
        Arrays.sort(correctArray);

        // make sure all other sorts give the same result as the in-built function does
        assertArrayEquals(correctArray, binarySorter        .sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, bubbleSorter        .sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, insertionSorter     .sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, mergeSorter         .sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, mergeIterativeSorter.sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, quickSorter         .sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, selectionSorter     .sort(numberArray).getSortedArray());
        assertArrayEquals(correctArray, treeBinarySorter    .sort(numberArray).getSortedArray());
    }
}
