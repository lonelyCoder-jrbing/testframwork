package com.testframwork.algorithm;

import java.util.*;

public class Demo02 {


    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
//        String[] strs = {"jrbing", "ingjrb", "int", "tin", "eat", "tea"};
        String[] strs = {""};
        List<List<String>> lists = demo02.groupAnagrams(strs);
        System.out.println(lists);
    }

    public List<List<String>> groupAnagrams(final String[] strs) {

        Map<Set<Character>, Integer> map = new HashMap<Set<Character>, Integer>();
        if (strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        if (strs.length == 1 && "".equals(strs[0])) {
            return new ArrayList<List<String>>() {{
                add(new ArrayList<String>() {{
                    add("");
                }});
            }};
        }


        for (String e : strs) {
            Set<Character> strSet = new HashSet<Character>();
            char[] chars = e.toCharArray();
            for (Character r : chars) {
                strSet.add(r);
            }

            map.put(strSet, e.length());
        }
        List<List<String>> result = new ArrayList<List<String>>(strs.length);

        Set<Map.Entry<Set<Character>, Integer>> entries = map.entrySet();
        for (Map.Entry<Set<Character>, Integer> e : entries) {
            Set<Character> set = e.getKey();
            Integer length = e.getValue();
            List<String> list = new ArrayList<String>();
            for (String string : strs) {
                char[] chars = string.toCharArray();
                Set<Character> chaSet = new HashSet<Character>();
                for (Character element : chars) {
                    chaSet.add(element);
                }
                if (string.length() == length) {
                    if (isSetEqual(chaSet, set)) {
                        list.add(string);
                    }
                }
            }
            result.add(list);
        }
        return result;
    }

    public boolean isSetEqual(Set set1, Set set2) {

        if (set1 == null && set2 == null) {
            return true; // Both are null
        }

        if (set1 == null || set2 == null || set1.size() != set2.size()
                || set1.size() == 0 || set2.size() == 0) {
            return false;
        }

        Iterator ite1 = set1.iterator();
        Iterator ite2 = set2.iterator();

        boolean isFullEqual = true;

        while (ite2.hasNext()) {
            if (!set1.contains(ite2.next())) {
                isFullEqual = false;
            }
        }

        return isFullEqual;
    }
}
