package model.E5strategy;

/**
 * Strategy策略设计模式主要是定义一系列的算法，把这些算法封装成单独的类，在运行时动态选择需要的算法
 * <p>
 * State状态模式和Strategy策略模式非常类似，但是有如下区别：
 * (1).State状态模式重点在于设定状态变化，根据状态，返回相应的响应。
 * (2).Strategy策略模式重点在于根据需求直接采用设定的策略，即根据场景选择合适的处理算法，而不需要改变状态。
 * JDK中策略模式的应用：
 * •java.util.concurrent.ThreadPoolExecutor.AbortPolicy
 * •java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
 * •java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy
 * •java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
 * •java.util.Comparator
 */
public class TextCharChange {
    public static void replace(TextStrategy strategy) {
        strategy.replace();
    }

    public static void main(String[] args) {
        String testText1 = "This is a test text!!@n Oh! Line Return!!@n";
        String testText2 = "This is a test text!!@r@n Oh! Line Return@r@n";
        TextCharChange.replace(new StrategyOne(testText2));
        TextCharChange.replace(new StrategyTwo(testText1));
    }
}  