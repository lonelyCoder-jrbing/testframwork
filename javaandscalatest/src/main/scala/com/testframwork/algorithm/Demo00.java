package com.testframwork.algorithm;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Demo00 {
    public static void main(String[] args) {
       String str = "{exitNum:12.000000, materialName:{materialName:测试物资名称, schemaCode:materialRepo, propertyType:SHORT_TEXT, displayCode:materialName, id:6b769f1b81ee48d5bb3b0c3dc6290992}, unit:KG, sortKey:0.000000, specification:XXX, model:OOO, remark=, id:e7a6cee12156452a978595969fdd5b13, parentId:610ca5fe54f04e738822fd360e01d8cb}";
        Map<String, Object> materialName = new HashMap<>();
        materialName.put("materialName","测试物资名称");
        materialName.put("schemaCode","materialRepo");
        HashMap<String, Object> objects = new HashMap<>();
        objects.put("exitNum",12.000000);
        objects.put("materialName",materialName);
        System.out.println(objects);
        System.out.println("materialName"+objects.get("materialName"));






        str = "";
       String str2 = "   ";
        System.out.println(str.equals(str2));
        boolean empty = StringUtils.isEmpty(str);
        System.out.println(empty);


    }


public void Permutation(char chs[],int start){

}


}
