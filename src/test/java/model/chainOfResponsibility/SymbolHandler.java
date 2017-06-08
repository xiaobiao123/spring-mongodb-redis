package model.chainOfResponsibility;

//特殊符号请求处理器  
class SymbolHandler extends Handler {

    public SymbolHandler(Handler successor) {
        super(successor);
    }

    public SymbolHandler() {

    }

    public void handRequest(Request request) {
        if (request.getType().equals("Symbol")) {
            System.out.println("Symbol has been handled");
        }
        //传递到下一个请求处理器处理
        else {
            getSuccessor().handRequest(request);
        }
    }
}  