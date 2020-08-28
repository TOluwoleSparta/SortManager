package com.sparta;

import static org.junit.Assert.assertArrayEquals;
import com.sparta.sorters.BubbleSorter;
import org.junit.Test;


public class BubbleSorterTests {
    BubbleSorter bubbleSorter = new BubbleSorter();

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

        assertArrayEquals(array1Sorted, bubbleSorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, bubbleSorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, bubbleSorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, bubbleSorter.sort(array4).getSortedArray());
    }

    @Test
    public void testSwap() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arraySwap12And65 = new int[]{65, 12, 43, 29, 78, 13}; // 65 and 12 swapped
        int[] arraySwap12And43 = new int[]{65, 43, 12, 29, 78, 13}; // 12 and 43 swapped
        int[] arraySwap12And13 = new int[]{65, 43, 13, 29, 78, 12}; // 12 and 13 swapped

        bubbleSorter.swap(array, 0, 1);
        assertArrayEquals(arraySwap12And65, array);

        bubbleSorter.swap(array, 1, 2);
        assertArrayEquals(arraySwap12And43, array);

        bubbleSorter.swap(array, 2, 5);
        assertArrayEquals(arraySwap12And13, array);
    }
}
