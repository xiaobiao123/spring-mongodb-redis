package model.V1vistor.test;

//具体元素
class ElementA implements IElement {

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
    @Override
    public void operation() {
        System.out.println("ElementA do operationA()……");
    }


}