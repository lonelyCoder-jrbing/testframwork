package redis.redisdemo.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * create by sumerian on 2020/4/11
 * <p>
 * desc:
 **/
@Data
@NoArgsConstructor
public class Student implements Serializable {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

}
