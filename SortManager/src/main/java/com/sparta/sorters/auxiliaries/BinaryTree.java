package com.sparta.sorters.auxiliaries;

import com.sparta.exceptions.tree.NodeNotFoundException;

public interface BinaryTree {
    int getRootElement() throws NodeNotFoundException;

    int getNumberOfElements();

    Node addElement(Node currentNode, int element);

    void addElements(int[] elements);

    boolean findElement(int value);

    int[] getSortedTreeAsc(Node currentNode, int[] array);

    int[] getSortedTreeDesc(Node currentNode, int[] array);
}
