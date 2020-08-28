package com.sparta;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.sparta.sorters.auxiliaries.Node;
import com.sparta.sorters.BinarySorter;
import org.junit.Test;

public class BinarySorterTests {
    BinarySorter binarySorter = new BinarySorter();

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

        assertArrayEquals(array1Sorted, binarySorter.sort(array1).getSortedArray());
        assertArrayEquals(array2Sorted, binarySorter.sort(array2).getSortedArray());
        assertArrayEquals(array3Sorted, binarySorter.sort(array3).getSortedArray());
        assertArrayEquals(array4Sorted, binarySorter.sort(array4).getSortedArray());
    }

    @Test
    public void testBuildTree() {
        int[] array1 = new int[]{12, 65, 43, 29, 78, 13};

        Node rootNode = binarySorter.buildTree(array1);
        Node treeNode;


        /*
          =====================
              EXPECTED TREE
          =====================

            12
                        65
                     43    78
                  29
               13
         */



        // first element - 12
        treeNode = rootNode;
        assertEquals(12, treeNode.getValue());
        assertEquals(null, treeNode.getLeftChild());
        assertEquals(65, treeNode.getRightChild().getValue());

        // second element - 65
        treeNode = treeNode.getRightChild();    // right of 12
        assertEquals(65, treeNode.getValue());
        assertEquals(43, treeNode.getLeftChild().getValue());
        assertEquals(78, treeNode.getRightChild().getValue());

        // third element - 43
        treeNode = treeNode.getLeftChild();     // left of 65
        assertEquals(43, treeNode.getValue());
        assertEquals(29, treeNode.getLeftChild().getValue());
        assertEquals(null, treeNode.getRightChild());

        // fourth element - 29
        treeNode = treeNode.getLeftChild();     // left of 29
        assertEquals(29, treeNode.getValue());
        assertEquals(13, treeNode.getLeftChild().getValue());
        assertEquals(null, treeNode.getRightChild());

        // fifth element - 78
        treeNode = rootNode;                    // back up to root node 12
        treeNode = treeNode.getRightChild();    // right of 12 to 65
        treeNode = treeNode.getRightChild();    // right of 65
        assertEquals(78, treeNode.getValue());
        assertEquals(null, treeNode.getLeftChild());
        assertEquals(null, treeNode.getRightChild());

        // sixth element - 13
        treeNode = rootNode;                    // back up to root node 12
        treeNode = treeNode.getRightChild();    // right of 12 to 65
        treeNode = treeNode.getLeftChild();     // left of 65 to 43
        treeNode = treeNode.getLeftChild();     // left of 43 to 29
        treeNode = treeNode.getLeftChild();     // left of 29
        assertEquals(13, treeNode.getValue());
        assertEquals(null, treeNode.getLeftChild());
        assertEquals(null, treeNode.getRightChild());
    }


}
