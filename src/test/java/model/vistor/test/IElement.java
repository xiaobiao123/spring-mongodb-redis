package model.vistor.test;

//抽象元素
interface IElement{  
    public void accept(IVisitor visitor);

    public void operation();
}  