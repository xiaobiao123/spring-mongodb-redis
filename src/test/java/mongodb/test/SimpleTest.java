package mongodb.test;

import java.net.UnknownHostException;

import com.alibaba.fastjson.JSON;
import com.mongodb.*;

public class SimpleTest {

	public static void main(String[] args) throws UnknownHostException, MongoException {
		//Mongo mg = new Mongo("172.30.22.9",27017);
		Mongo mg=new MongoClient("172.30.22.9",27017);

		// 查询所有的Database
		for (String name : mg.getDatabaseNames()) {
			System.out.println("dbName: " + name);
		}
		DB db = mg.getDB("admin");
		// 查询所有的聚集集合
		for (String name : db.getCollectionNames()) {
			System.out.println("collectionName: " + name);
		}
		DBCollection collection = db.getCollection("users");

		DBObject object=new BasicDBObject();
		object.put("name", "1");
		object.put("age", "2");


		//新增
		collection.insert(object);
		
		DBObject query =new BasicDBObject();
		query.put("name", "1");

		//根据查询条件查询信息
		System.out.println(JSON.toJSONString(collection.find(query)));
		// 查询所有的数据

		DBCursor cur = collection.find();
		while (cur.hasNext()) {
			System.out.println(cur.next());
			System.out.println(cur.count());
			System.out.println(cur.getCursorId());
			//System.out.println(JSON.serialize(cur));
		}
	
	}
}