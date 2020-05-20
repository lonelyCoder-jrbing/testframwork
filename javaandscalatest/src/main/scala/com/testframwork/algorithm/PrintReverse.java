package com.testframwork.algorithm;

/**
 * create by sumerian on 2020/5/10
 * <p>
 * desc:
 **/
public class PrintReverse {
    public static void main(String[] args) {
        String str = "woshijurongbing001";
        printreverse(str.toCharArray());
    }

    private static void printreverse(char[] str) {
        help(0, str);
    }


    private static void help(int index, char[] str) {
        if (str == null || index >= str.length) {
            return;
        }
        help(index + 1, str);
        //当上边的条件不满足的时候，开始出栈，栈的特点是后进先出，所以最后一个压栈的元素，第一个出栈。
        System.out.println(str[index]);
    }

}
