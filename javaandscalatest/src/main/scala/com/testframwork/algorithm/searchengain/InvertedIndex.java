package com.testframwork.algorithm.searchengain;

import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.util.*;

/**
 * create by sumerian on 2020/8/13
 * <p>
 * desc:
 **/
public class InvertedIndex {

    static HashMap<String,String>date = new HashMap();

    static {
        //存储数据map
        date.put("book1", "hello world");
        date.put("book2", "hello beautiful world");
        date.put("book3", "hello beautiful new world");
    }


    public static void main(String[] args) {
        //通过倒排索引去检索数据，例如现在要检索的数据为hello
        String str = "hello";
        Map<String, Set<String>> indexMap = getIndexMap();

        Set<String> strings = indexMap.get(str);
        for (String string : strings) {
            String s = date.get(string);
            System.out.println("result:  "+s);

        }


    }
   //建立倒排索引的map
    static Map<String, Set<String>> getIndexMap() {
        //索引的map
        HashMap<String,Set<String>>index = new HashMap();
        Set keySet = date.keySet();
        //遍历date的key
        for (Object word : keySet) {
            String keyString = (String) word;
            //通过key获得value
            String count = (String) date.get(keyString);
            //将字符串分割成数组
            String[] words = count.split(" ");
            //遍历数组，获取关键字
            for (int i = 0; i < words.length; i++) {
                //判断索引中是否存在关键字，如果不存在添加，存在将追加
                if (!index.containsKey(words[i])) {
                    //创建一个arrylist用来添加或后续追加索引值
//                    ArrayList books = new ArrayList();
                    Set<String> books = new TreeSet<>();
                    //将date的key存入
                    books.add(keyString);
                    //将新组建的键值对存入索引
                    index.put(words[i], books);
                } else {
                    //如果存在关键字,通过关键字获取value，
                    Set<String> books = (Set) index.get(words[i]);
                    //将date的key追加到value中
                    books.add(keyString);
                }
            }
        }
        //输出新map
        System.out.println(index);
        return index;
    }


}
