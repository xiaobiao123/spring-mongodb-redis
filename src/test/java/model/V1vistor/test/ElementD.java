package model.V1vistor.test;

/**
 * Created by GWB on 2017/6/7.
 */
public class ElementD implements  IElement {
    @Override
    public void accept(IVisitor visitor) {
    visitor.visit(this);
    }

    @Override
    public void operation() {
        System.out.println("ElementD do operationD() ....");

    }
}
