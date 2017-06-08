package model.chainOfResponsibility;

//数字请求处理器  
class NumberHandler extends Handler {

    public NumberHandler(Handler successor) {
        super(successor);
    }

    public NumberHandler() {
        super();
    }

    public void handRequest(Request request) {
        if (request.getType().equals("Number")) {
            System.out.println("Number has been handled");
        }
        //传递到下一个请求处理器处理
        else {
            getSuccessor().handRequest(request);
        }
    }
}  