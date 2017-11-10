package model.V1vistor;

//具体元素
class ElementA implements IElement {

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public void operationA() {
        System.out.println("ElementA do operationA()……");
    }


}