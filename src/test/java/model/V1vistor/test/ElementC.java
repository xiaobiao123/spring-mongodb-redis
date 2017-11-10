package model.V1vistor.test;

class ElementC implements IElement {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void operation() {
        System.out.println("ElementC do operationC()……");
    }
}  