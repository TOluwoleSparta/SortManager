package com.sparta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.sparta.sorters.BubbleSorter;
import com.sparta.sorters.QuickSorter;
import org.junit.Test;

public class QuickSorterTests {
    QuickSorter quickSorter = new QuickSorter();
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

        assertArrayEquals(array1Sorted, quickSorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, quickSorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, quickSorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, quickSorter.sort(array4).getSortedArray());
    }

    @Test
    public void testGetPivotIndex() {
        int[] array1 = new int[]{12, 65, 43, 29, 78, 13};  // random
        int[] array2 = new int[]{98, 97, 96, 95, 94, 93};  // descending (reverse) order
        int[] array3 = new int[]{56, -6, -1, 87, 29, 44};  // random including negatives
        int[] array4 = new int[]{-1, 12, 60, -9, 37, -1};  // random including duplicates

        int array1PivotIndexFrom0To5 = 1;    // 13 is greater than 1 element (12) in whole array
        int array2PivotIndexFrom2To4 = 2;    // 94 is the smallest in the partition [96, 95, 94]
        int array3PivotIndexFrom0To3 = 3;    // 87 is the greatest in the partition [56, -6, -1, 87]
        int array4PivotIndexFrom0To5 = 1;    // -1 is greater than 1 element (-9) in whole array

        assertEquals(array1PivotIndexFrom0To5, quickSorter.getPivotIndex(array1, 0, 5));
        assertEquals(array2PivotIndexFrom2To4, quickSorter.getPivotIndex(array2, 2, 4));
        assertEquals(array3PivotIndexFrom0To3, quickSorter.getPivotIndex(array3, 0, 3));
        assertEquals(array4PivotIndexFrom0To5, quickSorter.getPivotIndex(array4, 0, 5));
    }

    @Test
    public void testSortPartitions() {
        int[] array1 = new int[]{12, 65, 43, 29, 78, 13};  // random
        int[] array2 = new int[]{98, 97, 96, 95, 94, 93};  // descending (reverse) order
        int[] array3 = new int[]{56, -1, 87, -6, 29, 44};  // random including negatives
        int[] array4 = new int[]{-1, 12, 60, -9, 37, -1};  // random including duplicates

        int[] array1Sorted0To2 = new int[]{12, 43, 65, 29, 78, 13}; // sort [0] to [2] - [12, 65. 43]
        int[] array2Sorted3To5 = new int[]{98, 97, 96, 93, 94, 95}; // sort [2] to [5] - [95, 94, 93]
        int[] array3Sorted1To4 = new int[]{56, -6, -1, 29, 87, 44}; // sort [1] to [4] - [-1, 87, -6, 29]
        int[] array4Sorted0To5 = new int[]{-9, -1, -1, 12, 37, 60}; // sort [0] to [5] - [-1, 12, 60, -9, 37, -1]

        quickSorter.sortPartitions(array1, 0, 2);
        assertArrayEquals(array1Sorted0To2, array1);

        quickSorter.sortPartitions(array2, 3, 5);
        assertArrayEquals(array2Sorted3To5, array2);

        quickSorter.sortPartitions(array3, 1, 4);
        assertArrayEquals(array3Sorted1To4, array3);

        quickSorter.sortPartitions(array4, 0, 5);
        assertArrayEquals(array4Sorted0To5, array4);
    }

    @Test
    public void testSwap() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arraySwap12And65 = new int[]{65, 12, 43, 29, 78, 13}; // 65 and 12 swapped
        int[] arraySwap12And43 = new int[]{65, 43, 12, 29, 78, 13}; // 12 and 43 swapped
        int[] arraySwap12And13 = new int[]{65, 43, 13, 29, 78, 12}; // 12 and 13 swapped

        quickSorter.swap(array, 0, 1);
        assertArrayEquals(arraySwap12And65, array);

        quickSorter.swap(array, 1, 2);
        assertArrayEquals(arraySwap12And43, array);

        quickSorter.swap(array, 2, 5);
        assertArrayEquals(arraySwap12And13, array);
    }
}
