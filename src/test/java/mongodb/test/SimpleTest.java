package mongodb.test;

import java.net.UnknownHostException;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class SimpleTest {

	public static void main(String[] args) throws UnknownHostException, MongoException {
		Mongo mg = new Mongo("127.0.0.1",27017);
		// 查询所有的Database
		for (String name : mg.getDatabaseNames()) {
			System.out.println("dbName: " + name);
		}

		DB db = mg.getDB("test");
		// 查询所有的聚集集合
		for (String name : db.getCollectionNames()) {
			System.out.println("collectionName: " + name);
		}

		DBCollection users = db.getCollection("users");

		DBObject object=new BasicDBObject();
		object.put("name", "1");
		object.put("age", "1");
		
		users.insert(object);
		
		DBObject query =new BasicDBObject();
		query.put("name", "1");
		
		System.out.println(JSON.toJSONString(users.find(query)));
		
		
		
		// 查询所有的数据
		DBCursor cur = users.find();
		while (cur.hasNext()) {
			System.out.println(cur.next());
			System.out.println(cur.count());
			System.out.println(cur.getCursorId());
			//System.out.println(JSON.serialize(cur));
		}
	
	}
}