package model.F1factory.abst;

//IOS皮肤工厂
class IOSSkinFactory implements SkinFactory {
    public Style getStyle() {
        return new IOSStyle();
    }

    public Color getColor() {
        return new IOSColor();
    }
}  