package com.testframwork.algorithm;


import com.testframwork.algorithm.utils.ListNode;

import java.util.Random;

public class Demo01 {
    int i = 0;

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        ListNode node = null;
        ListNode listNode = demo01.madeListNode(10);
        ListNode listNode02 = demo01.madeListNode(10);
        ListNode listNode1 = demo01.mergeTwoLists(listNode, listNode02);


    }

    //使用尾插法生成链表
    public ListNode madeListNode(int len) {
        Random random = new Random();
        ListNode head, storage, last;
        head = storage = last = null;
        for (int i = 0; i < len; i++) {
            int temp = random.nextInt(50);
            last = new ListNode();
            last.val = temp;
            //判断头节点是否为空：
            if (head == null) {
                head = storage = last;
            } else {
                storage.next = last;
                storage = last;
            }
        }
        return head;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
