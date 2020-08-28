package com.sparta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.sparta.sorters.SelectionSorter;
import org.junit.Test;


public class SelectionSorterTests {
    SelectionSorter selectionSorter = new SelectionSorter();
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

        assertArrayEquals(array1Sorted, selectionSorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, selectionSorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, selectionSorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, selectionSorter.sort(array4).getSortedArray());
    }

    @Test
    public void testGetIndexOfSmallestElement() {
        int[] array1 = new int[]{12, 65, 43, 29, 78, 13};  // random
        int[] array2 = new int[]{98, 97, 96, 95, 94, 93};  // descending (reverse) order
        int[] array3 = new int[]{13, 14, 15, 16, 17, 18};  // ascending (correct) order
        int[] array4 = new int[]{50, 50, 50, 50, 50, 50};  // every element of equal value

        int array1SmallestElementFrom0To5 = 0;  // index of smallest element (12) in whole array is 0
        int array2SmallestElementFrom0To3 = 3;  // index of smallest element (94) in sub array [98, 97, 96, 95] is 3
        int array3SmallestElementFrom1To4 = 1;  // index of smallest element (14) in sub array [14, 15, 16, 17] is 1
        int array4SmallestElementFrom2To5 = 2;  // (first) index of smallest element (50) in sub array [50, 50, 50, 50] is 2

        assertEquals(array1SmallestElementFrom0To5, selectionSorter.getIndexOfSmallestElement(array1, 0, 5));
        assertEquals(array2SmallestElementFrom0To3, selectionSorter.getIndexOfSmallestElement(array2, 0, 3));
        assertEquals(array3SmallestElementFrom1To4, selectionSorter.getIndexOfSmallestElement(array3, 1, 4));
        assertEquals(array4SmallestElementFrom2To5, selectionSorter.getIndexOfSmallestElement(array4, 2, 5));
    }

    @Test
    public void testSwap() {
        int[] array = new int[]{12, 65, 43, 29, 78, 13};

        int[] arraySwap12And65 = new int[]{65, 12, 43, 29, 78, 13}; // 65 and 12 swapped
        int[] arraySwap12And43 = new int[]{65, 43, 12, 29, 78, 13}; // 12 and 43 swapped
        int[] arraySwap12And13 = new int[]{65, 43, 13, 29, 78, 12}; // 12 and 13 swapped

        selectionSorter.swap(array, 0, 1);
        assertArrayEquals(arraySwap12And65, array);

        selectionSorter.swap(array, 1, 2);
        assertArrayEquals(arraySwap12And43, array);

        selectionSorter.swap(array, 2, 5);
        assertArrayEquals(arraySwap12And13, array);
    }
}
