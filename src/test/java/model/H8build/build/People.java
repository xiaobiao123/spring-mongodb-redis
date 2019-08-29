package model.H8build.build;

import lombok.Data;
import model.H8build.NutritionFacts;

/**
 * @author gwb
 * @date 2019/8/27 11:57
 */
@Data
public class People {

    private String age;

    private String name;

    private String face;


    public static class PeopleBuild {

        private People people;

        private String age;

        private String name;

        private String face;


        public PeopleBuild age(String age) {
            this.age = age;
            return this;

        }

        public PeopleBuild name(String name) {
            this.name = name;
            return this;
        }

        public PeopleBuild face(String face) {
            this.face = face;
            return this;
        }

        public People build() {
            return new People(this);
        }
    }

    private People(People.PeopleBuild builder) {
        this.age = builder.age;
        this.name = builder.name;
        this.face = builder.face;
    }

    public static void main(String[] args) {
        People people = new PeopleBuild().age("").face("face").build();
    }
}
