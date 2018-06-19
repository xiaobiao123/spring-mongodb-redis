package model.K11composite.test;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Client {
    public static void main(String[] args) {
        AbsComp rootComp = new RootComp();
        rootComp.name = "根节点";

        AbsComp left = new RootComp();
        left.name = "left";
        rootComp.add(left);

        AbsComp right = new RootComp();
        right.name = "right";

        AbsComp child = new ChildComp();
        child.name = "child1";

        right.add(child);

        right.eachChild();

        rootComp.add(right);
        rootComp.eachChild();


    }
}
