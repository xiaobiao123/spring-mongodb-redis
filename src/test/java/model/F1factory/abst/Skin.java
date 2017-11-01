package model.F1factory.abst;

//软件皮肤类
class Skin {
    private SkinFactory skinFactory;

    public Skin(SkinFactory factory) {
        setSkinFactory(factory);
    }

    public void setSkinFactory(SkinFactory factory) {
        this.skinFactory = factory;
    }

    public void showSkin() {
        System.out.println("Style=");
        this.skinFactory.getStyle().showStyle();
        this.skinFactory.getColor().showColor();


    }
}  