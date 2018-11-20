package model.A1singleton;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 枚举单例
 */
public enum Singleton {
    INSTANCE("a", "123"),
    INSTANCE2("b", "123");

    Singleton(String key, String velue) {
        this.key = key;
        this.velue = velue;
    }

    private String key;
    private String velue;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVelue() {
        return velue;
    }


    public static Map<String, Singleton> map = Maps.newHashMap();

    static {
        for (Singleton item : Singleton.values()) {
            map.put(item.getKey(), item);
        }
    }


    public void setVelue(String velue) {
        this.velue = velue;
    }

    public void doSomeThing() {
    }


    public static Singleton getByKey(final String key) {
        if (key == null) {
            return null;
        }
        return map.get(key);
    }

    public static void main(String[] args) {
        Singleton s = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;

        System.out.println( Singleton.getByKey("a").getVelue());
    }


}