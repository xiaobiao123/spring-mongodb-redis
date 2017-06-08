package model.chainOfResponsibility;

//字母请求处理器  
class CharacterHandler extends Handler {
    public CharacterHandler(Handler successor) {
        super(successor);
    }

    public CharacterHandler() {

    }

    public void handRequest(Request request) {
        if (request.getType().equals("Character")) {
            System.out.println("Character has been handled");
        }
        //传递到下一个请求处理器处理
        else {
            getSuccessor().handRequest(request);
        }
    }
}  