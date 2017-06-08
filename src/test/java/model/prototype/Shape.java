package model.prototype;

//抽象原型类
abstract class Shape implements Cloneable {
    private String shapeName;

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    //实现了Colneable接口的类，可以使用clone()方法复制对象
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("此对象不支持复制");
        }
        return null;
    }
} 