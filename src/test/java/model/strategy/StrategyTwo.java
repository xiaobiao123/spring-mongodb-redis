package model.strategy;

//替换算法2：将文本中"@n"替换为"@r@n"
class StrategyTwo extends TextStrategy {   
    public StrategyTwo(String text) {   
        super(text);   
    }  
    public String replace() {   
        System.out.println("StrategyTwo:");  
        String result = text.replaceAll("@n", "@r@n");
        return result;   
    }  
}    