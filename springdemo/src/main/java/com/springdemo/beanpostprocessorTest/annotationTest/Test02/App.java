package com.springdemo.beanpostprocessorTest.annotationTest.Test02;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StudentDTO studentDTO = new StudentDTO("01", "jrbing");
        StudentDTO studentDTO1 = new StudentDTO("02", "boyingyue");
        StudentDTO studentDTO2 = new StudentDTO("", "jurongbing,boyingyue,meiyan");
        String sql01 = assembleSqlFromObj(studentDTO);
        System.out.println(sql01);
        String sql02 = assembleSqlFromObj(studentDTO1);
        System.out.println(sql02);
        String sql03 = assembleSqlFromObj(studentDTO2);
        System.out.println(sql03);

    }

    public static String assembleSqlFromObj(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取类上的注解
        Table table = (Table) obj.getClass().getAnnotation(Table.class);
        StringBuffer sql = new StringBuffer(47);
        //get table name
        String tableName = table.value();
        sql.append("select * from  " + tableName + " where 1=1");
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            String name = f.getName();
            String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            //获取属性上的注释
            Collum collum = f.getAnnotation(Collum.class);
            //如果属性值不为null的情况下，拼接查询条件
            if (collum != null) {
                //利用reflect 得到方法
                Method method = obj.getClass().getMethod(methodName);
                //get field vlue from instance
                String value = (String) method.invoke(obj);
                //when value is not null ,append string for sql
                if (value != null && value != "") {
                    //verify value if contains "id",if contains ,the condition may be equels
                    //if not contains ,the condition may be like or in
                    if (isNum(value) && isNum(collum.value())) {
                        //judge if the param is in
                        if (value.contains(",")) {
                            sql.append("and " + collum.value() + " in  (" + value + " )");
                        } else {
                            sql.append("and  " + collum.value() + " like" + " '%" + value + "%' ");
                        }

                    } else {
                        sql.append("and " + collum.value() + " =" + value + " ");
                    }
                }
            }
        }
        return sql.toString();
    }

    /**
     * check if the given value is Type id。
     * 1.check field type
     * 2.check field value
     */

    public static boolean isNum(String target) {
        boolean isNum = false;
        if (target.toLowerCase().contains("id")) {
            isNum = true;
        }
        if (target.matches("\\d+")) {
            isNum = true;
        }
        return isNum;


    }

}
