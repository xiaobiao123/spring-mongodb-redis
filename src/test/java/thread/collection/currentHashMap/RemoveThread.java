package thread.collection.currentHashMap;

/*
 * 
 * @author 薛定饿的猫
 * 
 * */
import java.util.Map.Entry;

public class RemoveThread extends Thread {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            for (int i = 0; i < 10000; i++) {
                sleep(2000);
                for(Entry<Integer, String> map:MainThread.pushmessage.entrySet()){
                    if (map.getKey()==i) {
                        System.out.println("成功收到id为："+map.getKey()+"返回的信息，删除该元素");
                        MainThread.pushmessage.remove(map.getKey());
                    }
                }
                System.out.println("内存对象中的元素数量为："+MainThread.pushmessage.size());
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}