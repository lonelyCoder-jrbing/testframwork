package com.testframwork.algorithm;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();


        int[] A = {2, 1, 4, 7, 3, 2, 5};
        int i = test.longestMountain(A);
        System.out.println("i="+i);


    }

    public int longestMountain(int[] A) {
        int i = getTop(A);
        int leftLen = getLeftLen(i, A);
        int rightLen = getRightLen(i, A);
        return leftLen + rightLen+1;


    }

    //寻找山峰的方法
    int getTop(int[] b) {
        for (int i = 1; i < b.length; i++) {
            if (b[i - 1] < b[i] && b[i] > b[i + 1]) {
                return i;
            }
        }
        return 0;
    }


    //小于的递归条件
    int getLeftLen(int i, int[] b) {
        int len = 0;
        for (int j = i; j >=1; j--) {
            if (b[j] > b[j - 1]) {
                len++;
            }
        }
        return len;
    }

    //大于的递归条件
    int getRightLen(int i, int[] b) {
        int len = 0;
        for (int j = i; j < b.length-1; j++) {
            if (b[j + 1] < b[j]) {
                len++;
            }
        }
        return len;
    }
}
