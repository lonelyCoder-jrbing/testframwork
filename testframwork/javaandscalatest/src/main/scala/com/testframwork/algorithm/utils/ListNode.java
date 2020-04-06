package com.testframwork.algorithm.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ListNode implements Iterable<ListNode> {
    public int val;
    public ListNode next;
    public ListNode head;
    public ListNode storage;
    public ListNode last;

    public ListNode() {


    }

    public ListNode(int val) {
        this.val = val;
    }

    public Iterator<ListNode> iterator() {


        return new myIterator();
    }
}

class myIterator implements Iterator {
    ListNode data;

//     public myIterator() {
//       this.data = head;
//     }

    public boolean hasNext() {
        return false;
    }

    public Object next() {
        return null;
    }

    public void remove() {

    }
}




