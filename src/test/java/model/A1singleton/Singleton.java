package model.A1singleton;

/**
 * 枚举单例
 */
public enum Singleton {
    INSTANCE;

    public void doSomeThing() {
    }

    public static void main(String[] args) {
        Singleton s = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;
        System.out.println(s == s2);
    }
}