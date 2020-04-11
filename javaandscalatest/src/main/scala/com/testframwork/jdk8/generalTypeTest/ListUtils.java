package com.testframwork.jdk8.generalTypeTest;

import java.util.AbstractList;
import java.util.List;

public class ListUtils {


    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        } else if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        } else {
            return new ListUtils.Partition(list, size);
        }
    }


    public static class Partition<T> extends AbstractList<List<T>> {

        public final List<T> list;
        public final int size;

        public Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(int index) {
            int listSize = this.size();
            if (listSize < 0) {
                throw new IllegalArgumentException("negative size: " + listSize);
            } else if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            } else if (index >= listSize) {
                throw new IndexOutOfBoundsException(
                        "Index " + index + " must be less than size " + listSize);
            } else {
                int start = index * this.size;
                int end = Math.min(start + this.size, this.list.size());
                return this.list.subList(start, end);
            }
//      return null;
        }

        @Override
        public int size() {
            return (this.list.size() + this.size - 1) / this.size;
        }
    }
}
