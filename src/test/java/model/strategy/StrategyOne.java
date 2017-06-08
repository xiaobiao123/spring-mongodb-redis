package model.strategy;

//替换算法1：将文本中"@r@n"替换为"@n"
class StrategyOne extends TextStrategy {
    public StrategyOne(String text) {
        super(text);
    }

    public String replace() {
        System.out.println("StrategyOne:");
        String result = text.replaceAll("@r@n", "@n");
        return result;
    }
}    