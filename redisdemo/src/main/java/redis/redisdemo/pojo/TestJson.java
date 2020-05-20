package redis.redisdemo.pojo;

import lombok.Data;

/**
 * create by sumerian on 2020/4/15
 * <p>
 * desc:
 **/
@Data
public class TestJson {
    private Student student;


    private User user;

    public static void main(String[] args) {
        TestJson testJson= new TestJson();
        Student student = new Student(1, "jurongbing", 15);
        User user = new User(1,"jurongbing",14);
        testJson.setStudent(student);
        testJson.setUser(user);

        System.out.println("json: "+testJson);


    }

}
