package com.util.tree;

import java.util.Stack;

public class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    //          创建一棵二叉树
    //                 15
    //           /            \
    //          62             28
    //        /   \          /   \
    //       41    17       16    19
    //      / \    / \     / \   /  \
    //     30 13 22  26   44 38 15  17


    public Node initial() {
        Node H = new Node(30);
        Node I = new Node(13);
        Node J = new Node(22);
        Node K = new Node(26);
        Node L = new Node(44);
        Node M = new Node(38);
        Node N = new Node(15);
        Node O = new Node(17);

        Node D = new Node(41, H, I);
        Node E = new Node(17, J, K);
        Node F = new Node(16, L, M);
        Node G = new Node(19, N, O);

        Node B = new Node(62, D, E);
        Node C = new Node(28, F, G);

        Node A = new Node(15, B, C);
        return A; //root
    }


    //递归实现【前序遍历】“根左右”
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getN() + " ");
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }

    //递归实现【中序遍历】“左根右”
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeftChild());
        System.out.print(node.getN() + " ");
        inOrder(node.getRightChild());
    }

    //递归实现【后序遍历】“左右根”
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeftChild());
        postOrder(node.getRightChild());
        System.out.print(node.getN() + " ");
    }


    //非递归实现【前序遍历】“根左右”
    public void iterativePreOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || stack.size() > 0) {
            //压入所有的左子节点，压入前先访问它
            while (node != null) {
                System.out.print(node.getN() + " ");
                stack.push(node);
                node = node.getLeftChild();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getRightChild();
            }
        }
    }

    //非递归实现【中序遍历】“左根右”
    public void iterativeInOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || stack.size() > 0) {
            //压入所有的左子节点，暂时先不访问
            while (node != null) {
                stack.push(node);
                node = node.getLeftChild();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                System.out.print(node.getN() + " ");
                node = node.getRightChild();
            }
        }
    }

    //非递归实现【后序遍历】双栈法 “左右根”
    public void iterativePostOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> temp = new Stack<>();
        while (node != null || stack.size() > 0) {
            //压入所有的右子节点，暂时先不访问
            while (node != null) {
                temp.push(node);
                stack.push(node);
                node = node.getRightChild();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getLeftChild();
            }
        }
        while (temp.size() > 0) {
            node = temp.pop();
            System.out.print(node.getN() + " ");
        }
    }

    public int getTreeDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int left = getTreeDepth(node.getLeftChild());
        int right = getTreeDepth(node.getRightChild());
        return (left < right) ? (right + 1) : (left + 1);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.setRoot(bt.initial());

        System.out.println("preOrder traversal of binary tree: ");
        bt.preOrder(bt.getRoot());
        System.out.println();
        bt.iterativePreOrder(bt.getRoot());
        System.out.println();

        System.out.println("inOrder traversal of binary tree: ");
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.iterativeInOrder(bt.getRoot());
        System.out.println();

        System.out.println("postOrder traversal of binary tree: ");
        bt.postOrder(bt.getRoot());
        System.out.println();
        bt.iterativePostOrder(bt.getRoot());
        System.out.println();

        System.out.print("the depth of binary tree: ");
        System.out.println(bt.getTreeDepth(bt.getRoot()));
    }


}
