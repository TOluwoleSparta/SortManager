package com.sparta.sorters;

import com.sparta.exceptions.tree.NodeNotFoundException;
import com.sparta.sorters.auxiliaries.BinaryTree;
import com.sparta.sorters.auxiliaries.Node;
import com.sparta.SortResults;
import java.util.Arrays;

public class TreeInterfaceBinarySorter implements Sorter, BinaryTree {

    private Node treeRoot;
    private int nodesInTree = 0;

    public int getRootElement() throws NodeNotFoundException {
        if (treeHasRoot()) {
            throw new NodeNotFoundException("Tree has no root.");
        } else {
            return treeRoot.getValue();
        }
    }

    public int getNumberOfElements() {
        return nodesInTree;
    }

    public Node addElement(Node currentNode, int element) {
        if(currentNode == null) {
            return new Node(element);
        } else {
            if (element <= currentNode.getValue()) {
                currentNode.setLeftChild(addElement(currentNode.getLeftChild(), element));
            } else if (element > currentNode.getValue()) {
                currentNode.setRightChild(addElement(currentNode.getRightChild(), element));
            }
        }
        return currentNode;
    }

    public void addElements(int[] elements) {
        int startIndex = 0;

        if (!treeHasRoot()) {
            treeRoot = new Node(elements[0]);
            startIndex = 1;
        }

        for (int index = startIndex; index < elements.length; index++) {
            addElement(treeRoot, elements[index]);
        }
    }

    public boolean findElement(int value) {
        return false;
    }

    public int[] getSortedTreeAsc(Node currentNode, int[] array) {
        if (currentNode != null) {
            array = getSortedTreeAsc(currentNode.getLeftChild(), array);

            array = Arrays.copyOf(array, array.length + 1);
            array[array.length - 1] = currentNode.getValue();

            array = getSortedTreeAsc(currentNode.getRightChild(), array);
        }

        return array;
    }

    public int[] getSortedTreeDesc(Node currentNode, int[] array) {
        if (currentNode != null) {
            array = getSortedTreeDesc(currentNode.getRightChild(), array);

            array = Arrays.copyOf(array, array.length + 1);
            array[array.length - 1] = currentNode.getValue();

            array = getSortedTreeDesc(currentNode.getLeftChild(), array);
        }

        return array;
    }

    //=== MY OWN FUNCTIONS ===========================================================

    public SortResults sort(int[] unsortedArray) {
        int[] sortedArray = new int[0];
        long startTime, sortTime;
        double sortTimeMilliSeconds;

        addElements(unsortedArray);

        startTime = System.nanoTime();

        sortedArray = getSortedTreeAsc(treeRoot, sortedArray);

        sortTime = System.nanoTime() - startTime;
        sortTimeMilliSeconds = ((double) sortTime) / 1_000_000.0;

        return new SortResults(unsortedArray,"Binary Sort (With Tree Implementation)", sortedArray, sortTimeMilliSeconds);
    }

    public boolean treeHasRoot() {
        return (treeRoot != null);
    }
}
