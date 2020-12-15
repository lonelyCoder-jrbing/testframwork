package com.testframwork.algorithm;


import java.util.*;


public class Demo03 {
    //字母异位词分组
    public static void main(String[] args) {
        Demo03 demo03 = new Demo03();
        String[] strs = {"tea", "eat", "tae", "int", "nts", "more"};
        List<List<String>> lists = demo03.groupAnagrams(strs);
        System.out.println(lists);
    }

    public List<List<String>> groupAnagrams(final String[] strs) {
        //将字符串数组中的元素全部取出,转换为char数组,进行排序,然后转为字符串
        String[] newStrs = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Set<Character> set = new TreeSet<Character>();
            for (Character el : chars) {
                set.add(el);
            }
            Object[] objects = set.toArray();
            StringBuffer sb = new StringBuffer(objects.length);
            for (Object e : objects) {
                sb.append((Character) e);
            }
            newStrs[i] = sb.toString();
        }
        List<List<String>> result = new ArrayList<List<String>>(strs.length);
        List<Integer> intList = new ArrayList<>();
        for (int j = 0; j < newStrs.length; j++) {
            String str = strs[j];
            List<String> list = null;
            boolean contains = intList.contains(j);
            if (!contains) {
                list = new ArrayList<String>(strs.length);
                list.add(strs[j]);
            }
            intList.add(j);
            for (int k = j + 1; k < newStrs.length; k++) {
                if (newStrs[j].equals(newStrs[k])) {
                    if (list != null) {
                        list.add(strs[k]);
                        intList.add(k);
                    }
                }
            }
            if (list != null) {
                result.add(list);
            }
        }
        return result;
    }
}