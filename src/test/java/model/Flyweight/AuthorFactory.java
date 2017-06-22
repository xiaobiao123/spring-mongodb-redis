package model.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight享元设计模式是为了避免大量拥有相同内容的小类重复创建，而使大家共享一个类的模式
 * <p>
 * Flyweight享元设计模式非常适合文字处理软件，因为像文字这种小对象重用的可能性很高，
 * 如果不共享对象，就会生成数量巨大的小对象消耗内存，享元模式则可以使重复概率高的对象重用，
 * 大大提高程序效率和性能。
 * 享元模式特点：
 * •享元模式基本是单例模式+简单工厂模式。
 * •减少运行时对象实例的个数，节省内存。
 * <p>
 * JDK中享元模式的应用：
 * •java.lang.Integer#valueOf(int)
 * •java.lang.Boolean#valueOf(boolean)
 * •java.lang.Byte#valueOf(byte)
 * •java.lang.Character#valueOf(char)
 * •String常量池
 * <p>
 * Flyweight享元设计模式使用对象池存放内部对象，当需要内部对象时首先判断对象池中是否存在，
 * 如果存在直接返回，如果不存在创建一个对象放入对象池中返回。
 */
//享元工厂
public class AuthorFactory {
    private static Map<String, Author> authors = new HashMap<String, Author>();

    public static Author getAuthor(String name) {
        Author author = authors.get(name);
        if (author == null) {
            author = new Author(name);
            authors.put(name, author);
        }
        return author;
    }

    public static void main(String[] args) {
        Integer.valueOf(10);
        Integer.valueOf(10);
    }
}  