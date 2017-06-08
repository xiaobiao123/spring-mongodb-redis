package model.vistor;

//抽象访问者
interface IVisitor {
    public void visit(ElementA element);

    public void visit(ElementB element);

    public void visit(ElementC element);
} 