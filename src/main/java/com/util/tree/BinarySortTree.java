package com.util.tree;

public class BinarySortTree {
    public Node root;
    public BinarySortTree(Node node) {
        this.root = node;
    }
    //节点数据结构
    private class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BinarySortTree(int value) {
        Node node = new Node(value);
        this.root = node;
    }

    //排序二叉树添加数据
    public Node addNode(Node node, int value) {
        if (node == null) {
            node = new Node(value);
        } else {
            if (value < node.value) {
                node.left = addNode(node.left, value);
            } else {
                node.right = addNode(node.right, value);
            }
        }
        return node;
    }

    //中序遍历
    public void sortNode(Node sortNode) {
        Node node = sortNode;
        if (node == null) {
            return;
        }
        sortNode(node.left);
        println(node.value);
        sortNode(node.right);
    }

    private void println(int value) {
        System.out.println(value);
    }


    public static void main(String... str) {
        BinarySortTree bTree = new BinarySortTree(10);
        bTree.root = bTree.addNode(bTree.root, 5);
        bTree.root = bTree.addNode(bTree.root, 20);
        bTree.root = bTree.addNode(bTree.root, 3);
        bTree.root = bTree.addNode(bTree.root, 8);
        bTree.root = bTree.addNode(bTree.root, 15);
        bTree.root = bTree.addNode(bTree.root, 25);
        bTree.sortNode(bTree.root);
    }
}
