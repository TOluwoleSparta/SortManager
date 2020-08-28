package com.sparta.sorters;

import com.sparta.sorters.auxiliaries.Node;
import com.sparta.SortResults;
import java.util.Arrays;

public class BinarySorter implements Sorter {

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = new int[0];
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        Node binaryTreeRoot = buildTree(unsortedArray);

        startTime = System.nanoTime();

        sortedArray = buildArray(binaryTreeRoot, sortedArray);

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Binary Sort", sortedArray, sortTimeMilliSeconds);
    }

    public Node buildTree(int[] array) {
        // first element in the table is the root node
        Node binaryTreeRootNode = new Node(array[0]);

        // for every element in the array (after the first one)
        for (int index = 1; index < array.length; index++) {
            placeElementInTree(binaryTreeRootNode, array[index]);   // start from root node
        }

        return binaryTreeRootNode;
    }

    public void placeElementInTree(Node currentNodeInTree, int element) {
        if (element <= currentNodeInTree.getValue()) {
            if (currentNodeInTree.hasLeftChild()) {
                placeElementInTree(currentNodeInTree.getLeftChild(), element);
            } else {
                // if the tree node has no left child, place new element node as the left child
                currentNodeInTree.setLeftChild(new Node(element));
            }

        } else if (element > currentNodeInTree.getValue()) {
            if (currentNodeInTree.hasRightChild()) {
                placeElementInTree(currentNodeInTree.getRightChild(), element);
            } else {
                // if the tree node has no right child, place new element node as the right child
                currentNodeInTree.setRightChild(new Node(element));
            }
        }
    }

    public int[] buildArray(Node currentNode, int[] array) {
        if (currentNode != null) {
            array = buildArray(currentNode.getLeftChild(), array);

            array = Arrays.copyOf(array, array.length + 1);
            array[array.length - 1] = currentNode.getValue();

            array = buildArray(currentNode.getRightChild(), array);
        }
        /*
        if (currentNode.hasLeftChild()) {
            array = buildArray(currentNode.getLeftChild(), array);
        }

        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = currentNode.getValue();

        if (currentNode.hasRightChild()) {
            array = buildArray(currentNode.getRightChild(), array);
        }
        */
        return array;
    }
}
