package model.H1build.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class TestMain {
    public static void main(String[] args) {

        Student.Builder builder = new Student.Builder();

        builder.address("xxx");
        builder.name("name");
        Student s = builder.build();
        System.out.println(s.getName());

        User user = new User.Builder("name", "age").getSet("set").build();

        System.out.println(user.getName());
    }
}
