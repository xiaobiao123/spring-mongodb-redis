package model.K1composite;

// 组合部件类
public class Leaf extends Component {

    // 叶子节点不具备添加的能力，所以不实现
    @Override
    public void add(Component c) {
        // TODO Auto-generated method stub
        System.out.println("");
    }

    // 叶子节点不具备添加的能力必然也不能删除
    @Override
    public void remove(Component c) {
        // TODO Auto-generated method stub
        System.out.println("");
    }

    // 叶子节点没有子节点所以显示自己的执行结果
    @Override
    public void eachChild() {
        // TODO Auto-generated method stub
        System.out.println(name + "Leaf执行了");
    }

}