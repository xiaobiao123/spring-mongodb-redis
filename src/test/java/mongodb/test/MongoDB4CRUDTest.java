package mongodb.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.util.JSON;

/**
 * <b>function:</b>实现MongoDB的CRUD操作
 *
 * @author hoojo
 * @version 1.0
 * @createDate 2011-6-2 下午03:21:23
 * @file MongoDB4CRUDTest.java
 * @package com.hoo.test
 * @project MongoDB
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 */
public class MongoDB4CRUDTest {

    private Mongo mg = null;
    private DB db;
    private DBCollection dbCollection;
    MongoClient mongoClient = new MongoClient();


    @Before
    public void init() throws UnknownHostException {
        try {
            //			mg = new Mongo();
            mongoClient = new MongoClient("172.30.22.9", 27017);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        // 获取temp DB；如果默认没有创建，mongodb会自动创建
        db = mongoClient.getDB("temp");
        // 获取users DBCollection；如果默认没有创建，mongodb会自动创建
        dbCollection = db.getCollection("users");
    }

    @After
    public void destory() {
        if (mg != null)
            mg.close();
        mg = null;
        db = null;
        dbCollection = null;
        System.gc();
    }

    public void print(Object o) {
        System.out.println(o);
    }

    // 3、 添加操作在添加操作之前，我们需要写个查询方法，来查询所有的数据。代码如下：

    /**
     * <b>function:</b> 查询所有数据
     *
     * @author hoojo
     * @createDate 2011-6-2 下午03:22:40
     */
    private void queryAll() {
        print("查询users的所有数据：");
        // db游标
        DBCursor cur = dbCollection.find();
        while (cur.hasNext()) {
            print(cur.next());
        }
    }

    /**
     * 新增方法 有save和insert
     * <p>
     * 2016年9月22日
     */
    @Test
    public void add() {
        // 先查询所有数据
        queryAll();
        print("count: " + dbCollection.count());

        DBObject user = new BasicDBObject();
        user.put("name", "hoojo");
        user.put("age", 289);


        DBObject dbObject = new BasicDBObject();
        dbObject.put("mongodbName", "name");
        dbObject.put("mongodbAge", 20);
        dbObject.put("title", "mongodb");
        dbObject.put("dec", "mongodb is a noSQL databases");

        dbCollection.insert(dbObject);


        //dbCollection.save(user).getN();//保存，getN()获取影响行数
        // print("xxxxxxxxxxxxxx"+dbCollection.save(user).getN());


        // 扩展字段，随意添加字段，不影响现有数据
        user.put("sex", "男");
        // print(users.save(user).getN());
        // print(dbCollection.insert(user).getN());


        // 添加多条数据，传递Array对象
        // print(users.insert(new BasicDBObject("name1", "tom1"), new
        // BasicDBObject("name", "tom")));

        // users.insert(new BasicDBObject("name","name"),new
        // BasicDBObject("name","name"));

        // 添加List集合
        List<DBObject> list = new ArrayList<DBObject>();
        list.add(user);
        DBObject user2 = new BasicDBObject("name", "lucy");
        user.put("age", 22);
        list.add(user2);
        // // 添加List集合
        print(dbCollection.insert(list));

        // // 查询下数据，看看是否添加成功
        // print("count: " + users.count());
        // queryAll();
    }

    // 4、 删除数据

    @Test
    public void remove() {
        queryAll();
        //print("删除id = 59f18a13c94b911bd42ea90f："+
        //dbCollection.remove(new BasicDBObject("_id",new ObjectId("5b81030f9a0c2922dc26d4d6")));


        System.out.println(dbCollection.remove(new BasicDBObject("mongodbAge", 20)).getN());

        //dbCollection.remove(new BasicDBObject("name","hoojo"));

        // print("remove name = hoojo: " + users.remove(new
        // BasicDBObject("name", new BasicDBObject("$gte", 24))));
        // print("remove name = hoojo: " + users.remove(new
        // BasicDBObject("name", "tom")));
    }

    // 5、 修改数据

    @Test
    public void modify() {
        //print("修改：" + dbCollection.update(new BasicDBObject("_id", new ObjectId("5b8102789a0c2914d8c0e9b8")),
        //        new BasicDBObject("age", 99)));

        //print("修改：" + dbCollection.update(new BasicDBObject("_id", new ObjectId("4dde2b06feb038463ff09042")),
        //        new BasicDBObject("age", 121), true, // 如果数据库不存在，是否添加
        //        false// 多条修改
        //));

        print("修改：" + dbCollection.update(new BasicDBObject("age", 121), new BasicDBObject("name", "dingding"),
                true, // 如果数据库不存在，是否添加
                true// false只修改第一天，true如果有多条就不修改
        ));

        //dbCollection.update(new BasicDBObject("_id",new ObjectId("59f18a85c94b9117745b83a5")),new BasicDBObject
        // ("mongodbAge","123"),true,false);


        //当数据库不存在就不修改、不添加数据，当多条数据就不修改
        print("修改多条：" + dbCollection.updateMulti(new BasicDBObject("_id", new
                ObjectId("4dde23616be7c19df07db42c")), new BasicDBObject("name",
                "199")));
    }

    // 6、 查询数据

    @Test
    public void query() {
        // 查询所有
        // queryAll();

        // 查询id = 4de73f7acd812d61b4626a77
        // print("find id = 59f18a85c94b9117745b83a3: "
        // + dbCollection.find(new BasicDBObject("_id", new
        // ObjectId("59f18a85c94b9117745b83a3"))).toArray());


        //System.out.println("====================================");
        //print(dbCollection.find(new BasicDBObject("mongodbName","name")).toArray());
        //System.out.println("====================================");
        // 查询age = 24

        //--查询条件
        BasicDBList condList = new BasicDBList();

        BasicDBObject cond1 = new BasicDBObject();
        cond1.append("age", 99);
        //condList.add(cond1);
        // 性别为女
        BasicDBObject cond2 = new BasicDBObject();
        cond2.append("sex", "男");
        condList.add(cond2);

        BasicDBObject cond = new BasicDBObject();
        cond.append("$and", condList);

        //排序
        //print("find age = 24: " + users.find(cond).sort(new BasicDBObject("age",1)).toArray());
        //分页
        print("find age = 24: " + dbCollection.find(cond).sort(new BasicDBObject("age", 1)).limit(5).skip(0).toArray());


        // 查询age >= 24
        print("find age >= 24: " + dbCollection.find(new BasicDBObject("age", new
                BasicDBObject("$gte", 24))).toArray());

        // print("find age <= 24: " + users.find(new BasicDBObject("age", new
        // BasicDBObject("$lte", 24))).toArray());

        print("查询age!=25：" + dbCollection.find(new BasicDBObject("age", new
                BasicDBObject(QueryOperators.NE, 25))).toArray());

        print("查询age in 25/26/27："
                + dbCollection.find(new BasicDBObject("age", new
                BasicDBObject(QueryOperators.IN, new int[]{25, 26, 27})))
                .toArray());

        print("查询age not in 25/26/27："
                + dbCollection.find(new BasicDBObject("age", new
                BasicDBObject(QueryOperators.NIN, new int[]{25, 26, 27})))
                .toArray());

        print("查询name exists 排序："
                + dbCollection.find(new BasicDBObject("name", new
                BasicDBObject(QueryOperators.EXISTS, true))).toArray());

        print("只查询age属性：" + dbCollection.find(null, new BasicDBObject("age",
                true)).toArray());

        print("只查属性：" + dbCollection.find(null, new BasicDBObject("age", true), 0, 2).toArray());
        print("只查属性：" + dbCollection.find(null, new BasicDBObject("age", true), 0,
                2, Bytes.QUERYOPTION_NOTIMEOUT).toArray());

        // 只查询一条数据，多条去第一条
         print("findOne: " + dbCollection.findOne());
         print("findOne: " + dbCollection.findOne(new BasicDBObject("age", 28)));
         print("findOne: " + dbCollection.findOne(new BasicDBObject("age", 28), new
         BasicDBObject("name", true)));

         // 查询修改、删除
         print("findAndRemove 查询age=25的数据，并且删除: " + dbCollection.findAndRemove(new
         BasicDBObject("age", 25)));

         // 查询age=26的数据，并且修改name的值为Abc
         print("findAndModify: " + dbCollection.findAndModify(new
         BasicDBObject("age", 26), new BasicDBObject("name", "Abc")));


         print("findAndModify: " + dbCollection.findAndModify(new
         BasicDBObject("age", 28), // 查询age=28的数据
         new BasicDBObject("name", true), // 查询name属性
         new BasicDBObject("age", true), // 按照age排序
         false, // 是否删除，true表示删除
         new BasicDBObject("name", "Abc"), // 修改的值，将name修改成Abc
         true, true));
        //
        // queryAll();
    }
    // mongoDB不支持联合查询、子查询，这需要我们自己在程序中完成。将查询的结果集在Java查询中进行需要的过滤即可。

    // 7、 其他操作
    @Test
    public void testOthers() {
        DBObject user = new BasicDBObject();
        user.put("name", "hoojo");
        user.put("age", 24);

        // JSON 对象转换
        print("serialize: " + JSON.serialize(user));
        // 反序列化
        print("parse: " + JSON.parse("{ \"name\" : \"hoojo\" , \"age\" : 24}"));

        print("判断temp Collection是否存在: " + db.collectionExists("temp"));

        // 如果不存在就创建
        if (!db.collectionExists("account1")) {
            DBObject options = new BasicDBObject();
            options.put("size", 20);
            options.put("capped", true);
            options.put("max", 20);
            print(db.createCollection("account1", options));
        }

        // 设置db为只读
        // WriteConcern ss =new WriteConcern();
        // db.setWriteConcern(ss);
        ReadConcern readConcern = new ReadConcern(ReadConcernLevel.LOCAL);
        db.setReadConcern(readConcern);
        // 只读不能写入数据
        db.getCollection("test").save(user);
    }


    class Persion {

        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }


    }
}