package model.H8build.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class User {
    private String name;

    private String age;

    private String sex;

    public static class Builder {

        private User user;

        public Builder(String name, String age) {
            user=new User();
            user.name = name;
            user.age = age;
        }

        public Builder getSet(String sex) {
            user.sex = sex;
            return this;
        }

        public User build(){
            return user;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
