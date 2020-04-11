package com.testframwork.algorithm;/*
给定二叉搜索树（BST）的根节点和一个值。
你需要在BST中找到节点值等于给定值的节点。
 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 */


import java.util.LinkedList;
import java.util.List;

public class Demo06 {
    private int[] array = {4, 2, 7, 1, 3};
    private static List<TreeNode> nodeList = null;


    public static void main(String[] args) {
        Demo06 demo06 = new Demo06();
        demo06.createbineryTree();


        TreeNode treeNode = demo06.searchBST(nodeList.get(0), 2);
        System.out.println(treeNode.val);
        System.out.println(treeNode.left.val);
        System.out.println(treeNode.right.val);


    }


    public TreeNode searchBST(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.val > value) {
            return searchBST(root.left, value);
        } else if (root.val < value) {
            return searchBST(root.right, value);
        } else {
            return root;
        }
    }

    public void createbineryTree() {

        nodeList = new LinkedList<TreeNode>();
        // 将一个数组的值依次转换为Node节点
        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            nodeList.add(new TreeNode(array[nodeIndex]));
        }
// 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            // 左孩子
            nodeList.get(parentIndex).left = nodeList
                    .get(parentIndex * 2 + 1);
            // 右孩子
            nodeList.get(parentIndex).right = nodeList
                    .get(parentIndex * 2 + 2);
        }

        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).left = nodeList
                .get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).right = nodeList
                    .get(lastParentIndex * 2 + 2);
        }

    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}