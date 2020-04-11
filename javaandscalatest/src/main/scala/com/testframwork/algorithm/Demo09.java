package com.testframwork.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Demo09 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int getDecimalValue(ListNode head) {
        //先取出节点中的值
        // List<Integer> list = new ArrayList<>();
        //  list.add(head.val);

        int i = 0;
        ListNode node = null;
        node = head;
        int nums = 0;
//        nums =head.val*(1 << 0);
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        while (node.next != null) {
            i++;
            System.out.println("node==" + node.next.val);
            ;
            list.add(node.next.val);
//            nums += node.next.val * (1<<i);
//            System.out.println(nums);
            node = node.next;
        }
        int[] intArray = new int[list.size()];
            int k = list.size() - 1;
        for (int j = 0; j < list.size(); j++) {
            System.out.println("list====" + list.get(j));
            System.out.println("k---"+k);
            intArray[k] = list.get(j);
            k--;
        }
        for (int i1 = 0; i1 < intArray.length; i1++) {
            int number = intArray[i1];
            System.out.println("++" + number);
            nums += intArray[i1] * (1 << i1);
            System.out.println("nums"+nums);
        }

        return nums;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(1);
//        head.next.next.next.next = new ListNode(1);
        Demo09 demo09 = new Demo09();
        int decimalValue = demo09.getDecimalValue(head);
        System.out.println(decimalValue);

    }
}
