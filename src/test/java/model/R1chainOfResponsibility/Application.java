package model.R1chainOfResponsibility;

/**
 * 责任链设计模式是用一系列请求处理器试图处理一个请求，这些请求处理器之间是一个松散耦合，
 * 唯一的共同点是在他们之间传递请求。例如客户端发送一个请求，请求处理器A先处理，如果A没
 * 有处理或者无法处理，就将请求传递给请求处理器B，如果B没有处理或者无法处理，就将请求传
 * 递到请求处理器C去处理，所有这些请求处理器构成一条请求处理责任链
 *
 * (1).Java的异常处理机制，当程序中发生异常时，try-catch会比较所捕捉的异常是否符合异常类型，
 * 如果符合就执行所设定的处理，如果都没有比对到适当的异常，就会将异常丢出try-catch区块之外。
 */
public class Application {
    public static void main(String[] args) {
        Handler numberHandler = new NumberHandler();
        Handler characterHandler = new CharacterHandler();
        Handler symbolHandler = new SymbolHandler();

        numberHandler.setSuccessor(characterHandler);


        characterHandler.setSuccessor(symbolHandler);

        symbolHandler.setSuccessor(numberHandler);


        Request request1 = new Request("Number");
        //Request request2 = new Request("Character");
        //Request request3 = new Request("Symbol");

        characterHandler.handRequest(request1);
        //characterHandler.handRequest(request2);
        //characterHandler.handRequest(request3);
    }
}  