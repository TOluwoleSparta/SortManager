package com.sparta;

import com.sparta.sorters.MergeSorter;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSorterTests {
    MergeSorter mergeSorter = new MergeSorter();

    @Test
    public void testSort() {
        int[] array1 = new int[]{12, 65, 43, 29, 78, 13};  // random
        int[] array2 = new int[]{98, 97, 96, 95, 94, 93};  // descending (reverse) order
        int[] array3 = new int[]{13, 14, 15, 16, 17, 18};  // ascending (correct) order
        int[] array4 = new int[]{50, 50, 50, 50, 50, 50};  // every element of equal value

        int[] array1Sorted = new int[]{12, 13, 29, 43, 65, 78};
        int[] array2Sorted = new int[]{93, 94, 95, 96, 97, 98};
        int[] array3Sorted = new int[]{13, 14, 15, 16, 17, 18};
        int[] array4Sorted = new int[]{50, 50, 50, 50, 50, 50};

        assertArrayEquals(array1Sorted, mergeSorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, mergeSorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, mergeSorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, mergeSorter.sort(array4).getSortedArray());
    }

    @Test
    public void testFillSubArray() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arrayFromIndex0Length3    = new int[] {12, 65, 43};       // array[0] to array[2]
        int[] arrayFromIndex1Length4    = new int[] {65, 43, 29, 78};   // array[1] to array[4]
        int[] arrayFromIndex5Length1    = new int[] {13};               // array[5] to array[5]
        int[] arrayFromIndex3Length0    = new int[] {};                 // array[3} but blank

        assertArrayEquals(arrayFromIndex0Length3, mergeSorter.fillSubArray(array, 0, 3));
        assertArrayEquals(arrayFromIndex1Length4, mergeSorter.fillSubArray(array, 1, 4));
        assertArrayEquals(arrayFromIndex5Length1, mergeSorter.fillSubArray(array, 5, 1));
        assertArrayEquals(arrayFromIndex3Length0, mergeSorter.fillSubArray(array, 3, 0));
    }

    @Test
    public void testSortSubArrays() {
        int[] array1 = new int[]{12, 65, 43, 29, 78, 13};  // random
        int[] array2 = new int[]{98, 97, 96, 95, 94, 93};  // descending (reverse) order
        int[] array3 = new int[]{56, -1, 87, -6, 29, 44};  // random including negatives
        int[] array4 = new int[]{-1, 12, 60, -9, 37, -1};  // random including duplicates

        int[] array1Sorted0To2 = new int[]{12, 43, 65, 29, 78, 13}; // sort [0] to [2] - [12, 65. 43]
        int[] array2Sorted3To5 = new int[]{98, 97, 96, 93, 94, 95}; // sort [2] to [5] - [95, 94, 93]
        int[] array3Sorted1To4 = new int[]{56, -6, -1, 29, 87, 44}; // sort [1] to [4] - [-1, 87, -6, 29]
        int[] array4Sorted0To5 = new int[]{-9, -1, -1, 12, 37, 60}; // sort [0] to [5] - [-1, 12, 60, -9, 37, -1]

        mergeSorter.sortSubArrays(array1, 0, 2);
        assertArrayEquals(array1Sorted0To2, array1);

        mergeSorter.sortSubArrays(array2, 3, 5);
        assertArrayEquals(array2Sorted3To5, array2);

        mergeSorter.sortSubArrays(array3, 1, 4);
        assertArrayEquals(array3Sorted1To4, array3);

        mergeSorter.sortSubArrays(array4, 0, 5);
        assertArrayEquals(array4Sorted0To5, array4);
    }

    @Test
    public void testMerge() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arrayMerge0To1And2        = new int[]{12, 43, 65, 29, 78, 13};  // after merging sub arrays [12, 43] and [65]
        int[] arrayMerge1To2And3To4     = new int[]{12, 29, 43, 65, 78, 13};  // after merging sub arrays [43, 65] and [29, 78]
        int[] arrayMerge3ToNothing      = new int[]{12, 29, 43, 65, 78, 13};  // after merging sub array [65] to nothing

        mergeSorter.mergeSubArrays(array, 0, 1, 2);
        assertArrayEquals(arrayMerge0To1And2, array);

        mergeSorter.mergeSubArrays(array, 1, 2, 4);
        assertArrayEquals(arrayMerge1To2And3To4, array);

        mergeSorter.mergeSubArrays(array, 3, 3, 3);
        assertArrayEquals(arrayMerge3ToNothing, array);
    }
}
