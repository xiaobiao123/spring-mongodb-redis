package model.H8build;

/**
 * Created by Administrator on 2017/6/7.
 * <p>
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
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
