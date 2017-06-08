package model.chainOfResponsibility;

//Request请求类
class Request {
    private String type;

    public Request(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}  