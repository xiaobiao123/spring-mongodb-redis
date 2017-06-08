package model.Flyweight;

//将Author作者类设计为可共享的享元
class Author {
    //内部状态  
    private String name;

    public String getName() {
        return name;
    }

    public Author(String name) {
        this.name = name;
    }
}  