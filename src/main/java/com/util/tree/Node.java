package com.util.tree;

public class Node {
    private int n;
    private Node leftChild;
    private Node rightChild;

    public Node(int n){
        this(n, null, null);
    }
    public Node(int n, Node leftChild, Node rightChild){
        this.n = n;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getN() {
        return n;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

}
