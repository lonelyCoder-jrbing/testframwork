package com.testframwork.algorithm;

import java.util.*;

public class Demo08 {


    public static void main(String[] args) {
        String str01 = "(()())(())(()(()))";
        String str02 = "(()())(())";
        String str03 = "(()())(())(()(()))";

        String s = removeOuterParenthesesjieyin(str03);
        System.out.println(s);
    }


    public static String removeOuterParenthesesjieyin(String S) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            set.add(0);
            if (S.charAt(i) == '(') {
                count++;
            } else if (S.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
                set.add(i);
                if(i+1<S.length()){
                    set.add(i+1);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < S.length(); i++) {
            if (!set.contains(i)) {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();

    }


    //"("的右边的字符为"("则删除第一个,
    //如果"("右边的字符为")"则保留并且存入map中

    public static String removeOuterParentheses(String S) {
        StringBuffer sb = new StringBuffer();
        List<Integer> list = new ArrayList<>();

        //记录index在list中
        for (int i = 0; i < S.length() - 1; i++) {
            if (S.charAt(i) == '(' && S.charAt(i + 1) == '(') {
                list.add(i);
            }

            if (S.charAt(i) == '(' && S.charAt(i + 1) == ')') {
                sb.append("()");
            }
        }
        return sb.toString();
    }

    public static String removeOuterParentheses01(String S) {
        StringBuffer sb = new StringBuffer();
        List<Integer> list = new ArrayList<>();

        //记录index在list中
        for (int i = 0; i < S.length() - 1; i++) {
            if (S.charAt(i) == '(' && S.charAt(i + 1) == '(') {
                list.add(i);
            }
        }
        for (int j = S.length() - 1; j > 0; j--) {
            if (S.charAt(j) == ')' && S.charAt(j - 1) == ')') {
                list.add(j);
            }
        }

        for (int i = 0; i < S.length(); i++) {
            if (!list.contains(i)) {
                sb.append(S.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String removeOuterParentheses02(String S) {
        //先记录最内层是括号的index
        List<Integer> list = new ArrayList<>();
        List<Integer> list01 = new ArrayList<>();

        char[] chars = S.toCharArray();
        for (int i = 0; i < S.length() - 1; i++) {
            if (S.charAt(i) == '(' && S.charAt(i + 1) == ')') {
                chars[i] = '*';
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < S.length(); i++) {

            if (chars[i] == '(') {
                map.put('(', i);
                sb2.append(chars[i]);
            } else {
                map.put(')', i);
                sb2.append(chars[i]);
            }
        }
        map.forEach((k, v) -> {


        });
        return null;
    }

    public static String removeOuterParentheses03(String S) {
        //统计出连续的'(' 和连续的')'
        Set<Character> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < S.length(); i++) {
            for (int j = i + 1; j < S.length(); j++) {
                if (S.charAt(i) == '(' && S.charAt(j) == '(' && S.charAt(j + 1) == '(') {
                    list.add(i);
                }
            }

        }

        for (int i = S.length() - 1; i > 0; i--) {
            for (int j = i - 1; j - 1 > 0; j--) {
                if (S.charAt(i) == ')' && S.charAt(j) == ')' && S.charAt(j - 1) == ')') {
                    list.add(i);
                }
            }
        }
        char[] chars = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            if (!list.contains(i)) {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
