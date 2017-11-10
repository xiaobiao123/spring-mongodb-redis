package model.V1vistor.test;

//具体访问者
class MyVisitor implements IVisitor {
    public void visit(IElement element) {
        element.operation();
    }

    //public void visit(ElementB element) {
    //    element.operationB();
    //}
    //
    //public void visit(ElementC element) {
    //    element.operationC();
    //}
}

