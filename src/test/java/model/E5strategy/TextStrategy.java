package model.E5strategy;
//文本替换策略

abstract class TextStrategy {   
    protected String text;  
  
    public TextStrategy(String text) {   
        this.text = text;   
    }  
    public abstract String replace();   
}    