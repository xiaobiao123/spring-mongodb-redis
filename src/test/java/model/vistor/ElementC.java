package model.vistor;

class ElementC implements IElement {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public void operationC() {
        System.out.println("ElementC do operationC()……");
    }
}  