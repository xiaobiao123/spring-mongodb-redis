package model.F6factory.simple;

class FactoryCreater {
    public static ICode getCodingSkill(String type) {
        ICode iCode = null;
        if ("android".equalsIgnoreCase(type)) {
            iCode = new CodeImplAndroid();
        }
        else if ("ios".equalsIgnoreCase(type)) {
            iCode = new CodeImplIOS();
        }
        else if ("php".equalsIgnoreCase(type)) {
            iCode = new CodeImplPHP();
        }
        return iCode;
    }
}