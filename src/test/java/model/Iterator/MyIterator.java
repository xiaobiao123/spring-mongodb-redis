package model.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Iterator迭代器模式，提供一种统一的方法访问一个容器（Container）对象中各个元素，
 * 而又不需暴露该对象的内部细节，迭代器模式是为容器而设计
 * <p>
 * 1) 迭代器角色（Iterator）：迭代器角色负责定义访问和遍历元素的接口。
 * 2) 具体迭代器角色（Concrete Iterator）：具体迭代器角色要实现迭代器接口，并要记录遍历中的当前位置。
 * 3) 容器角色（Container）：容器角色负责提供创建具体迭代器角色的接口。
 * 4) 具体容器角色（Concrete Container）：具体容器角色实现创建具体迭代器角色的接口——这个具体迭代器角色于该容器的结构相关
 */
public class MyIterator implements Iterable {
    //存放数据的集合
    private ArrayList list;

    //负责创建具体迭代器角色的工厂方法
    public Iterator iterator() {
        return new Itr(list);
    }

    //作为内部类的具体迭代器角色   
    private class Itr implements Iterator {
        ArrayList myList;
        int position = 0;

        public Itr(ArrayList list) {
            this.myList = list;
        }

        public Object next() {
            Object obj = myList.get(position);
            position++;
            return obj;
        }

        public boolean hasNext() {
            if (position >= myList.size()) {
                return false;
            } else {
                return true;
            }
        }

        //不支持remove操作  
        public void remove() {
            throw new UnsupportedOperationException(
                    "Alternating MyIterator does not support remove()");
        }
    }
}