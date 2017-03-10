package mongodb.example;  
  
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mongodb.example.data.UserDao;
import mongodb.example.data.impl.UserDaoImpl;
import mongodb.example.data.model.UserEntity;  
  
public class ApplicationSpring {  
  
    public static void main(String[] args) {  
  
        System.out.println("Bootstrapping HelloMongo");  
  
//        context = new ClassPathXmlApplicationContext("classpath:conf/applicationContext.xml");  
      ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:conf/applicationContext.xml" });
       
      UserDao userDao = context.getBean(UserDaoImpl.class);  
        
        userDao._test();  
        UserEntity entity1 = new UserEntity();  
        entity1.setId("5");  
        entity1.setAge(1);  
        entity1.setBirth(new Date());  
        entity1.setPassword("asdfasdf");  
        entity1.setRegionName("北京");  
        entity1.setWorks(1);  
        userDao.insert(entity1);  
        userDao.update(entity1);  
        userDao.createCollection();  
//      
//        List<UserEntity> list = userDao.findList(0, 10);  
//        for (UserEntity e : list) {  
//            System.out.println("all - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial())  
//                    + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());  
//        }  
//  
//        list = userDao.findListByAge(1);  
//        for (UserEntity e : list) {  
//            System.out.println("age=1 - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special="  
//                    + Arrays.toString(e.getSpecial()) + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());  
//        }  
//  
//        UserEntity e = userDao.findOne("1");  
//        System.out.println("id=1 - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial())  
//                + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());  
//  
//        e = userDao.findOneByUsername("limingnihao");  
//        System.out.println("username=limingnihao - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword() + ", regionName=" + e.getRegionName() + ", special="  
//                + Arrays.toString(e.getSpecial()) + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());  
//  
//          
        System.out.println("DONE!");  
    }  
  
}  