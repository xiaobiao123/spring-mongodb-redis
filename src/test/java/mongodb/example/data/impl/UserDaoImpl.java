package mongodb.example.data.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import mongodb.example.data.UserDao;
import mongodb.example.data.model.Message;
import mongodb.example.data.model.NameEntity;
import mongodb.example.data.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {

    public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void _test() {
        Set<String> colls = this.mongoTemplate.getCollectionNames();
        for (String coll : colls) {
            logger.info("CollectionName=" + coll);
        }
        DB db = this.mongoTemplate.getDb();
        logger.info("db=" + db.toString());
    }

    @Override
    public void createCollection() {
        if (!this.mongoTemplate.collectionExists(UserEntity.class)) {
            this.mongoTemplate.createCollection(UserEntity.class);
        }
    }

    @Override
    public List<UserEntity> findList(int skip, int limit) {
        Query query = new Query();
        query.with(new Sort(new Order(Direction.DESC, "age")));
        query.skip(skip).limit(limit);
        return this.mongoTemplate.find(query, UserEntity.class);
    }


    @Override
    public List<UserEntity> findListByAge(int age) {
        Query query = new Query();
        query.addCriteria(new Criteria("age").gt(age));
        query.skip(0).limit(10);
        return this.mongoTemplate.find(query, UserEntity.class);
    }


    @Override
    public List<UserEntity> findListByAge2(int age) {

        // 常用查询
        // 分级查询
        // 模糊查询

        // 查询字段不为空的数据 -----关键字---ne-----Criteria.where("key1").ne("").ne(null)

        // 查询或语句：a || b ----- 关键字---orOperator
        // Criteria criteria = new Criteria();
        // criteria.orOperator(Criteria.where("key1").is("0"),Criteria.where("key1").is(null));

        Query query = new Query();
        // query.addCriteria(Criteria.where("age").ne("").ne(null)); //
        // 查询字段不为空的数据
        // -----关键字---ne
        query.addCriteria(new Criteria("age").is(age));
        // query.with(new Sort("birth")); 排序
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "birth")));
        // query.addCriteria(Criteria.where("age").lte(age));//小于等于

        // query.addCriteria(Criteria.where("age").gte(age));//大于等于
        // query.addCriteria(Criteria.where("password").is("123456"));// and
        return this.mongoTemplate.find(query, UserEntity.class);
    }

    @Override
    public UserEntity findOne(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, UserEntity.class);
    }

    @Override
    public UserEntity findOneByUsername(String username) {
        Query query = new Query();
        query.addCriteria(new Criteria("regionName").is(username));
        return this.mongoTemplate.findOne(query, UserEntity.class);
    }

    @Override
    public void insert(UserEntity entity) {
        this.mongoTemplate.insert(entity);

    }

    @Override
    public int update(UserEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria("age").is(entity.getAge()));
        Update update = new Update();
        update.set("age", entity.getAge());
        update.set("password", entity.getPassword());
        update.set("regionName", entity.getRegionName());
        update.set("special", entity.getSpecial());
        update.set("works", entity.getWorks());
        update.set("name", entity.getName());
        return this.mongoTemplate.updateMulti(query, update, UserEntity.class).getN();
    }

    public void updateMulti(UserEntity entity) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(entity.getId()));
        Update update = new Update();
        update.set("age", entity.getAge());
        update.set("password", entity.getPassword());
        update.set("regionName", entity.getRegionName());
        update.set("special", entity.getSpecial());
        update.set("works", entity.getWorks());
        update.set("name", entity.getName());
        this.mongoTemplate.updateMulti(query, update, UserEntity.class);

    }

    @Override
    public void insertNameEntity(NameEntity entity) {
        this.mongoTemplate.insert(entity);
    }

    @Override
    public void remove(UserEntity entity) {
        // 通过对象删除
        // this.mongoTemplate.remove(entity);

        // 指定删除条件
        Query query = new Query();
        query.addCriteria(Criteria.where("regionName").is(entity.getRegionName()));
        System.out.println(this.mongoTemplate.remove(query, entity.getClass()).getN());
    }

    @Override
    public List<UserEntity> findListByAgeSortByBirth(int age) {
        Query query = new Query();
        query.addCriteria(new Criteria("age").is(age));
//        query.with(new Sort(Direction.DESC,"birth"));
//        query.with(new Sort("birth")); 排序
        query.with(new Sort(Sort.Direction.ASC, "birth"));

        return this.mongoTemplate.find(query, UserEntity.class);
    }

    @Override
    public List<UserEntity> findPage(int age) {
        Query query = new Query();
        query.addCriteria(new Criteria("age").is(age));
        query.limit(1).skip(0);
        return this.mongoTemplate.find(query, UserEntity.class);
    }

    /**
     * 大数据量数据分页优化
     *
     * @param page
     * @param pageSize
     * @param lastId   上一页的最大id
     * @return
     */
    public List<Message> largePageList(int page, int pageSize, int lastId) {
        // DBCollection userCollection = mongoTemplate.getCollection("message");
        //
        //// System.out.println(JSONObject.toJSON(userCollection.find().sort(new
        // BasicDBObject("id", 1)).limit(pageSize)));
        // DBCursor limit = null;
        // if (page == 1) {
        // limit = userCollection.find().sort(new BasicDBObject("id",
        // 1)).limit(pageSize);
        // } else {
        // limit = userCollection.find(new BasicDBObject("id",new
        // BasicDBObject(operations))).sort(new BasicDBObject("id",
        // 1)).limit(pageSize);
        // System.out.println(JSONObject.toJSON(limit));
        // }
        //
        // System.out.println(JSONObject.toJSON(limit));
        // List<Message> userList = (List<Message>)
        // JSON.parseArray(JSON.toJSONString(limit), Message.class);
        // return userList;

        Query query = new Query();

        mongoTemplate.count(query, Message.class);

        if (page > 1) {
            query.addCriteria(new Criteria("id").gt(lastId));
        }
        // query.addCriteria(new Criteria("").)//添加其他条件
        query.limit(10);
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
        List<Message> list = this.mongoTemplate.find(query, Message.class);
        System.out.println(JSONObject.toJSON(list));
        return list;
    }

    @Override
    public Long count() {
        Query query=new Query();
        query.addCriteria(Criteria.where("age").gt(20));
        return this.mongoTemplate.count(query,UserEntity.class);
    }

    @Override
    public List<UserEntity> findListGtAge(int age) {
        Query query=new Query();
        query.addCriteria(new Criteria("age").gte(age));
        query.with(new Sort(Direction.DESC,"age"));
        return this.mongoTemplate.find(query,UserEntity.class);
    }

    @Override
    public void aggregate() {
        GroupBy groupBy = GroupBy.key("age").initialDocument("{count:0}")
                .reduceFunction("function(key, values){values.count+=1;}");
        System.out.println(JSON.toJSON(mongoTemplate.group("mg_user", groupBy, UserEntity.class)));

//		Query query=new Query();
//		
//		query.addCriteria(new Criteria().where("sendTime").is("2016-09-26 15:37:44"));
//		
//		System.out.println(JSON.toJSON(mongoTemplate.count(query, Message.class)));

    }


    @Override
    public void distinc() {

        DB db = this.mongoTemplate.getDb();
        DBCollection collection = db.getCollection("mg_user");
        DBObject object = new BasicDBObject();

//		 collection.group(new BasicDBObject("name",1), 
//				 new BasicDBObject("_id",new BasicDBObject("$gt",120000)), null, null);


        object.put("_id", new BasicDBObject("$gt", 120000));

        List distinc = collection.distinct("age", object);


        System.out.println(JSON.toJSON(distinc));

    }
}