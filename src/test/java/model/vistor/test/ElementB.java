package model.vistor.test;

class ElementB implements IElement {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
    @Override
    public void operation() {
        System.out.println("ElementB do operationB()……");
    }
}