package com.sparta;

import com.sparta.sorters.InsertionSorter;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSorterTests {
    InsertionSorter insertionSorter = new InsertionSorter();

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

        assertArrayEquals(array1Sorted, insertionSorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, insertionSorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, insertionSorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, insertionSorter.sort(array4).getSortedArray());
    }


}
