package com.sparta;

import com.sparta.sorters.MergeIterativeSorter;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeIterativeSorterTests {
    MergeIterativeSorter mergeIterativeSorter = new MergeIterativeSorter();

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

        assertArrayEquals(array1Sorted, mergeIterativeSorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, mergeIterativeSorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, mergeIterativeSorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, mergeIterativeSorter.sort(array4).getSortedArray());
    }

    @Test
    public void testFillSubArray() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arrayFromIndex0Length3    = new int[] {12, 65, 43};       // array[0] to array[2]
        int[] arrayFromIndex1Length4    = new int[] {65, 43, 29, 78};   // array[1] to array[4]
        int[] arrayFromIndex5Length1    = new int[] {13};               // array[5] to array[5]
        int[] arrayFromIndex3Length0    = new int[] {};                 // array[3} but blank

        assertArrayEquals(arrayFromIndex0Length3, mergeIterativeSorter.fillSubArray(array, 0, 3));
        assertArrayEquals(arrayFromIndex1Length4, mergeIterativeSorter.fillSubArray(array, 1, 4));
        assertArrayEquals(arrayFromIndex5Length1, mergeIterativeSorter.fillSubArray(array, 5, 1));
        assertArrayEquals(arrayFromIndex3Length0, mergeIterativeSorter.fillSubArray(array, 3, 0));
    }

    @Test
    public void testMerge() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arrayMerge0To1And2        = new int[]{12, 43, 65, 29, 78, 13};  // after merging sub arrays [12, 43] and [65]
        int[] arrayMerge1To2And3To4     = new int[]{12, 29, 43, 65, 78, 13};  // after merging sub arrays [43, 65] and [29, 78]
        int[] arrayMerge3ToNothing      = new int[]{12, 29, 43, 65, 78, 13};  // after merging sub array [65] to nothing

        mergeIterativeSorter.mergeSubArrays(array, 0, 1, 2);
        assertArrayEquals(arrayMerge0To1And2, array);

        mergeIterativeSorter.mergeSubArrays(array, 1, 2, 4);
        assertArrayEquals(arrayMerge1To2And3To4, array);

        mergeIterativeSorter.mergeSubArrays(array, 3, 3, 3);
        assertArrayEquals(arrayMerge3ToNothing, array);
    }
}
