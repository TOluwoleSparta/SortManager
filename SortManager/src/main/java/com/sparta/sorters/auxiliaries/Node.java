package com.sparta.sorters.auxiliaries;


public class Node {
    private final int value;
    private Node leftChild;
    private Node rightChild;

    public Node(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getValue() {
        return value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node childNode) {
        this.leftChild = childNode;
    }

    public void setRightChild(Node childNode) {
        this.rightChild = childNode;
    }

    public boolean hasLeftChild() {
        return (leftChild != null);
    }

    public boolean hasRightChild() {
        return (rightChild != null);
    }
}
