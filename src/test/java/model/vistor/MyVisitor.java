package model.vistor;

//具体访问者
class MyVisitor implements IVisitor {
    public void visit(ElementA element) {
        element.operationA();
    }

    public void visit(ElementB element) {
        element.operationB();
    }

    public void visit(ElementC element) {
        element.operationC();
    }
}

