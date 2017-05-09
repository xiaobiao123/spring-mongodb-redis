package mongodb.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import mongodb.example.data.UserDao;
import mongodb.example.data.model.NameEntity;
import mongodb.example.data.model.UserEntity;

//https://www.zhihu.com/question/32071167?sort=created
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongodbTest {

    private static Logger logger = Logger.getLogger(MongodbTest.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    // @Before
    // public void before() {
    //
    // @SuppressWarnings("resource")
    // ApplicationContext context = new ClassPathXmlApplicationContext(
    // new String[] { "classpath:conf/applicationContext.xml" });
    // userDao = context.getBean(UserDaoImpl.class);
    //
    // }

    @Test
    public void _test() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        System.out.println("1....................." + Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            UserEntity entity = new UserEntity();
            entity.setAge(i);
            entity.setRegionName("regionName" + i);
            entity.setBirth(new Date());
            executorService.execute(new ThreadCustomerCert(entity, userDao, i));

        }
        Thread.sleep(1000);
        executorService.shutdown();
        if (executorService.isTerminated()) {
            System.out.println(".........................");
        }
    }

    private static class ThreadCustomerCert implements Runnable {
        private UserEntity entity;

        private UserDao userDao;
        private int num;

        public ThreadCustomerCert(UserEntity entity, UserDao userDao, int num) {
            this.entity = entity;
            this.userDao = userDao;
            this.num = num;
        }

        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public synchronized void run() {
            logger.info(num + "....................." + Thread.currentThread().getName());
            userDao.insert(entity);
        }
    }

    @Test
    public void findList() {
        List<UserEntity> list = userDao.findList(1, 10);
        for (UserEntity uentity : list) {
            System.out.println("==================" + JSONObject.toJSON(uentity));
        }
    }

    @Test
    public void findListByAge() {
        List<UserEntity> list = userDao.findListByAge(10);
        for (UserEntity uentity : list) {
            System.out.println("==================" + JSONObject.toJSON(uentity));
        }
    }

    @Test
    public void findOne() {
        UserEntity uentity = userDao.findOne("58c1104f45815e1b7c8a4d94");
        System.out.println("==================" + JSONObject.toJSON(uentity));
    }

    @Test
    public void findOneByUsername() {
        UserEntity uentity = userDao.findOneByUsername("regionName88");
        System.out.println("==================" + JSONObject.toJSON(uentity));
    }

    @Test
    public void insertUser() {
        UserEntity entity = new UserEntity();
        entity.setAge(12);
        entity.setRegionName("添加户测试");
        entity.setBirth(new Date());
        Long start = System.currentTimeMillis();
        userDao.insert(entity);
    }

    @Test
    public void updateUser() {
        UserEntity entity = new UserEntity();
        entity.setAge(10);
        entity.setBirth(new Date());
        entity.setRegionName("regionName11");
        entity.setPassword("123456");
        System.out.println("=================" + userDao.update(entity));

    }

    @Test
    public void remove() {
        UserEntity entity = new UserEntity();
        entity.setId("57e614f3605de019e865a271");
        entity.setRegionName("regionName10");
        userDao.remove(entity);

    }

    @Test
    public void findListByAgeSortByBirth() {
        List<UserEntity> list = userDao.findListByAgeSortByBirth(12);
        for (UserEntity uentity : list) {
            System.out.println("==================" + JSONObject.toJSON(uentity));
        }
    }


    @Test
    public void addNameEntity() {
        NameEntity entity = new NameEntity();
        Long start = System.currentTimeMillis();
        userDao.insertNameEntity(entity);
        System.out.println(System.currentTimeMillis() - start);
        List<UserEntity> list = userDao.findListByAge(12);
        for (UserEntity uentity : list) {
            System.out.println(JSONObject.toJSON(uentity));
        }
    }


    @Test
    public void findSort() {
        List<UserEntity> list = userDao.findListByAgeSortByBirth(12);
        for (UserEntity uentity : list) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(format.format(uentity.getBirth()));
            System.out.println(JSONObject.toJSON(uentity));
        }

    }

    @Test
    public void findPage() throws Exception {
//        List<UserEntity> list = userDao.findPage(12);
//        for (UserEntity uentity : list) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            System.out.println(format.format(uentity.getBirth()));
//            System.out.println(JSONObject.toJSON(uentity));
//        }

        UserEntity userEntity = userDao.findOne("5811f0f22e33c21510e3ea38");
        Class<UserEntity> cl = (Class<UserEntity>) userEntity.getClass();

        Method method[] = cl.getMethods();
        for (int i = 0; i < method.length; ++i) {
            // Class<?> returnType = method[i].getReturnType();
            // Class<?> para[] = method[i].getParameterTypes();
            // System.out.print(returnType.getName() + " ");
            // System.out.print(method[i].getName() + " ");
            if (method[i].getName().equals("getStr")) {
                System.out.println(method[i].getName());
                Class<?> returnType = method[i].getReturnType();
                Class<?> para[] = method[i].getParameterTypes();
                for (int kk = 0; kk < para.length; kk++) {
                    System.out.print(para[kk].getName() + " ");
                }
                System.out.print(returnType.getName() + "  ");
                method[i].invoke(userEntity, "xxxxxxxxxxxxxx", "sssssssssssssss");
            }
        }

        // Field[] fds = cl.getDeclaredFields();
        // for (Field field : fds) {
        // System.out.println(field.getName());
        // System.out.println(field.get(userEntity));
        // }

    }

    @Test
    public void largePageList() {
        userDao.largePageList(2, 10, 10);

    }

    @Test
    public void aggregate() {
        userDao.aggregate();
    }

    @Test
    public void distinct() {
        userDao.distinc();
    }


    @Test
    public void count() {
        System.out.println("======================" + userDao.count());

    }

    @Test
    public void findListGtAge() {
        List<UserEntity> list = userDao.findListGtAge(88);
        for (UserEntity uentity : list) {
            System.out.println("======================" + JSON.toJSONString(uentity));
        }
    }


}
