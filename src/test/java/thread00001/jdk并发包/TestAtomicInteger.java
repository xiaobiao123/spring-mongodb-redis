package thread00001.jdk并发包;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/3/23.
 */
public class TestAtomicInteger {

    AtomicInteger integer=new AtomicInteger(10);


    private void  add(){
        integer.addAndGet(1);
    }

    private AtomicInteger get(){
        return integer;
    }


    public static void main(String[] args) {
        TestAtomicInteger  testAtomicInteger=new TestAtomicInteger();

        TestAtomicInteger  testAtomicInteger2=new TestAtomicInteger();



    }

}
