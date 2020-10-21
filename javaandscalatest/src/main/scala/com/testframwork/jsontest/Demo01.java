package com.testframwork.jsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo01 {
    @Setter
    public static class DemoClass {
        private String Name;

        @JSONField(name = "Name")
        public String getName() {
            return Name;
        }
    }

    public static void main(String[] args) {
        String json = "{OrgFullName=laoren, schemaCode=OrgBasePropertity, propertyType=SHORT_TEXT, displayCode=OrgFullName, id=df472759d6b6412190a62c0a576a06a0}";

        JSONArray jsonArray = JSON.parseArray(json.replace("{","[").replace("}","]"));
        System.out.println(jsonArray);

        Demo01 demo01 = new Demo01();
        String id = demo01.getElementByIndex(json, "id");
        System.out.println("id==========:   " + id);


        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("name", "jurongbing");
        System.out.println(stringObjectHashMap);

        DemoClass demoClass = new DemoClass();
        demoClass.setName("jurongbing");
        System.out.println(JSON.toJSONString(demoClass));
//        Demo01 demo01 = new Demo01();
//        String s = demo01.StrUtil(json);
        Boolean flag = true;
        if(Objects.nonNull(flag)){
            System.out.println("============");
        }






    }

    private String StrUtil(String oldStr) {
        int length = oldStr.split(",").length;
        String[] split = oldStr
                .replace("{", "")
                .replace("}", "")
                .replace(" ", "")
                .split(",")[length - 1].split("=");

        return split[split.length - 1];
    }

    private String getElementByIndex(String oldStr, String keyStr) {
        Map<String, String[]> collect = Arrays
                .stream(oldStr.replace("{", "")
                        .replace("}", "")
                        .replace(" ", "")
                        .split(","))
                .map(el -> el.split("="))
                .collect(Collectors.toMap(v -> Arrays.stream(v).findFirst().get(),
                        Function.identity(), (k1, k2) -> k1));
        return collect.get(keyStr)[1];
    }
}
