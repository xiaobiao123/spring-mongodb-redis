package apo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;

public class Logger {
    public static void start() {
        System.out.println(new Date() + " say hello start...");
    }

    public static void end() {
        System.out.println(new Date() + " say hello end");

        //强引用
        Object object = new Object();

        //软引用
        SoftReference<String> reference=new SoftReference<String>(new String("xxx"));

        //弱引用
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));

        //虚引用
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());


    }
}