package model.F6factory.simple.simple1;

//图形工厂
class ShapeFactory{  
    public static Shape createShape(String name) throws InstantiationException,   
                                      IllegalAccessException,   
                                      ClassNotFoundException  
    {  
    //使用java的反射机制来产生对象实例
    return (Shape)Class.forName(name).newInstance();
}  
}   