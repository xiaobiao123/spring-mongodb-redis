package model.composite;


/**
 * 抽象构件角色（component）：是组合中的对象声明接口，在适当的情况下，实现所有类共有接口的默认行为。
 * 声明一个接口用于访问和管理Component子部件。
 * <p>
 * 这个接口可  以用来管理所有的子对象。(可选)在递归结构中定义一个接口，用于访问一个父部件，并在合适的情况下实现它。
 * 树叶构件角色(Leaf)：在组合树中表示叶节点对象，叶节点没有子节点。并在组合中定义图元对象的行为。
 * 树枝构件角色（Composite）：定义有子部件的那些部件的行为。存储子部件。在Component接口中实现与子部件有关的操作。
 * 客户角色（Client）：通过component接口操纵组合部件的对象。
 */

public class ComponentDemo {

    public static void main(String[] args) {
        // 构造根节点
        Composite rootComposite = new Composite();
        rootComposite.name = "根节点";

        // 左节点
        Composite compositeLeft = new Composite();
        compositeLeft.name = "左节点";

        // 构建右节点，添加两个叶子几点，也就是子部件
        Composite compositeRight = new Composite();
        compositeRight.name = "右节点";
        Leaf leaf1 = new Leaf();
        leaf1.name = "右-子节点1";
        Leaf leaf2 = new Leaf();
        leaf2.name = "右-子节点2";
        compositeRight.add(leaf1);
        compositeRight.add(leaf2);

        // 左右节点加入 根节点
        rootComposite.add(compositeRight);
        rootComposite.add(compositeLeft);
        // 遍历组合部件
        rootComposite.eachChild();
    }
}