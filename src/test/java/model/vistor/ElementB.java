package model.vistor;

class ElementB implements IElement {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public void operationB() {
        System.out.println("ElementB do operationB()……");
    }
}