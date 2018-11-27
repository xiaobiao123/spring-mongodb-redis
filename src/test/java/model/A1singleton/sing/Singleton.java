package model.A1singleton.sing;

/**
 * 枚举单例:默认枚举实例的创建是线程安全的，并且在任何情况下都是单例
 * <p>
 * 枚举单例的优点就是简单，但是大部分应用开发很少用枚举，可读性并不是很高，不建议用
 */
public enum Singleton {
    INSTANCE("", "");

    public void doSomeThing() {
    }

    private final String status;
    private final String value;

    Singleton(String status, String value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }


}