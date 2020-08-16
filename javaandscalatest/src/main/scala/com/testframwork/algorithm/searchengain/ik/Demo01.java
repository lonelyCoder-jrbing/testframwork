package com.testframwork.algorithm.searchengain.ik;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * create by sumerian on 2020/8/13
 * <p>
 * desc:使用ik实现倒排索引 0.1v
 **/
public class Demo01 {
    static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("绝句1", "两个黄鹂鸣翠柳,一行白鹭上青天。窗含西岭千秋雪,门泊东吴万里船。");
        map.put("登高", "风急天高猿啸哀,渚清沙白鸟飞回。无边落木萧萧下,不尽长江滚滚来。");
        map.put("绝句2", "江边踏青罢,回首见旌旗。风起春城暮,高楼鼓角悲。");
    }

    public static void main(String[] args) throws IOException {

        Map<String, Set<String>> indexMap = getIndexMap();
        String searchContext = "旌旗";
        Set<String> strings = indexMap.get(searchContext);
        if (strings != null) {
            strings.forEach(Demo01::print);
        }


    }

    public static void print(String str) {

        System.out.println("poem name:  " + str);
        String content = map.get(str);
        System.out.println("content:  " + content);
    }

    //建立倒排索引的map
    static Map<String, Set<String>> getIndexMap() throws IOException {
        IKAnalyzer analyzer = new IKAnalyzer(true);
        //索引的map
        HashMap<String, Set<String>> index = new HashMap();
        Set keySet = map.keySet();
        //遍历date的key
        TokenStream tokenStream = null;
        for (Object word : keySet) {
            String keyString = (String) word;
            //通过key获得value
            String count = (String) map.get(keyString);
            //将字符串分割成数组
            tokenStream = analyzer.tokenStream("", count);
            tokenStream.reset();
            CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
            int l = 0;
            //输出分词器和处理结果
            StringBuffer sb = new StringBuffer();
//            System.out.println(analyzer.getClass());
            String[] words = new String[Integer.MAX_VALUE / 1000];
            int j = 0;
            while (tokenStream.incrementToken()) {
                words[j] = term.toString();
                j++;
//                System.out.println(term.toString() + "|");
//                sb.append(term.toString() + "|");
                l += term.toString().length();
                //如果一行输出的字数大于30，就换行输出
                if (l > 30) {
                    System.out.println();
                    l = 0;
                }
            }
            String s = map.get(keyString);
            String[] split = s.split("。");
            for (String s1 : split) {
                String[] split1 = s1.split(",");
                for (String s2 : split1) {
                    j++;
                    words[j] = s2;
                }
            }


            tokenStream.close();

            //遍历数组，获取关键字
            for (int i = 0; i < words.length; i++) {
                //判断索引中是否存在关键字，如果不存在添加，存在将追加

                if (!index.containsKey(words[i])) {
                    //创建一个arrylist用来添加或后续追加索引值
                    Set<String> books = new TreeSet<>();
                    //将date的key存入
                    books.add(keyString);
                    //将新组建的键值对存入索引
//                    System.out.println("words[i]:  " + words[i]);
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
//        System.out.println("index:   " + index);
        return index;
    }

}
