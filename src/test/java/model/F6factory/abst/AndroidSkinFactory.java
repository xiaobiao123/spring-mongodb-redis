package model.F6factory.abst;

//Android皮肤工厂
class AndroidSkinFactory implements SkinFactory {
    public Style getStyle() {
        return new AndroidStyle();
    }

    public Color getColor() {
        return new AndroidColor();
    }
}  