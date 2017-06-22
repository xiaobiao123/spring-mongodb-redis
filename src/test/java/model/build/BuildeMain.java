package model.build;

/**
 * Created by Administrator on 2017/6/7.
 */
public class BuildeMain {
    public static void main(String[] args) {
        NutritionFacts build = new NutritionFacts
                .Builder(1, 2)
                .calories(1)
                .carbohydrate(2)
                .build();

    }
}
