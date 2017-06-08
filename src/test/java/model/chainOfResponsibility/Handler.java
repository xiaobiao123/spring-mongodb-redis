package model.chainOfResponsibility;

//抽象请求处理器
abstract class Handler {
    private Handler successor;

    public Handler() {
    }

    public Handler(Handler successor) {
        this.successor = successor;
    }

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public void handRequest(Request request){}
}