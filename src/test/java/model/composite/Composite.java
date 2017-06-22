package model.composite;

import java.util.ArrayList;
import java.util.List;

// 组合类
public class Composite extends Component {

    // 用来保存节点的子节点
    List<Component> list = new ArrayList<Component>();

    // 添加节点 添加部件
    @Override
    public void add(Component c) {
        // TODO Auto-generated method stub
        list.add(c);
    }

    // 删除节点 删除部件
    @Override
    public void remove(Component c) {
        // TODO Auto-generated method stub
        list.remove(c);
    }

    // 遍历子节点
    @Override
    public void eachChild() {
        // TODO Auto-generated method stub
        System.out.println(name + "执行了");
        for (Component c : list) {
            System.out.println(c.name + "===================");
            c.eachChild();
        }
    }
}
