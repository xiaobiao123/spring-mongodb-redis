package thread.collection.currentHashMap;
/*
 * 
 * @author 薛定饿的猫
 * 
 * */
import java.util.concurrent.ConcurrentHashMap;

public class MainThread {
    public static ConcurrentHashMap<Integer, String> pushmessage=new ConcurrentHashMap<Integer,String>();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            pushmessage.put(i, "该消息是id为"+i+"的消息");
        }
        Thread pushThread=new PushThread();
        Thread remove=new RemoveThread();
        pushThread.start();
        remove.start();
        for (int i = 10; i < 20; i++) {
            pushmessage.put(i, "又一波到来，消息是id为"+i+"的消息");
        }
    }
}