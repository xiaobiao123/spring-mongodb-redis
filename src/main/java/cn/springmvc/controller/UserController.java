package cn.springmvc.controller;

import cn.springmvc.commen.RandomDataUtil;
import cn.springmvc.commen.ReturnData;
import cn.springmvc.model.User;
import cn.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    private static Executor executor = Executors.newFixedThreadPool(10);

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100000),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static AtomicLong integer = new AtomicLong(0);

    private Lock lock = new ReentrantLock();

    private static ArrayBlockingQueue<User> blockingQueue = new ArrayBlockingQueue<User>(100);


    static {
        //秒杀的商品数量放在redis集群中，
        integer = new AtomicLong(20);
    }

    @Autowired
    private UserService userService;

    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(User user) {
        ReturnData returnData = new ReturnData();
        user.setState(1);
        String nickName = System.currentTimeMillis()+""+ RandomDataUtil.SixNumRadom();
        user.setNickname(nickName);
//        user.setName("name"+integer.addAndGet(1));
//        user.setMark("mark"+integer.addAndGet(1));
//        user.setPassword("password"+integer.getAndAdd(1));
        //System.out.println(integer.addAndGet(1));
        userService.insertUser(user);
//        User rtUser = userService.getUser(nickName);
        returnData.setData(user);
        return returnData.toString();
    }

    @RequestMapping("index")
    public String index() {
        System.out.println("this is test");
        return "index";
    }

    //    @RequestMapping("index3")
//    public String index3() {
//        logger.info("loadRunner" + "========================");
//        if (integer.get() > 0) {
//            executor.execute(new Runnable() {
//                @Override
//                public synchronized void run() {
//                    integer.getAndAdd(-1);
//                    userService.updateUser(1l);
//                    logger.error("AtomicInteger测试:" + 11);
//                }
//            });
//        } else {
//            logger.error("商品已经售完了");
//        }
//        return "index";
//    }
//
//
    @RequestMapping("index2")
    @ResponseBody
    public String index2(String name,String age) {
        logger.debug("This is a debug messagexxxxxxxxxxxxxxxxxxxx!");
        logger.info("This is info messagexxxxxxxxxxxxxxxxxxxxxxx!");
        logger.warn("This is a warn messagexxxxxxxxxxxxxxxxxxxxxxxx!");
        logger.error("This is error messagexxxxxxxxxxxxxxxxxxxxxxxxxx!");

       // userService.getUser("");

        try{
            System.out.println(5/0);
        }catch(Exception e){
            logger.error(e,e.getCause());
        }
        return "index";

    }

}
