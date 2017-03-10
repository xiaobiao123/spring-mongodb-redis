package mongodb.example;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongodbTest1 {


	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Test
	public void insertStateStats(){  
		StateStats st=new StateStats();
		st.setContent("content1");
		st.setFromNick("test");
		st.setFromUid("1313131313123");
		st.setToUid("gwb123");
		st.setUnreadCount(2l);
		mongoTemplate.insert(st);
    } 
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void findNewMessage() {  
		String userId="gwb";
        Long total = 0l;  
        String reduce = "function(doc, aggr){" +  
                "            aggr.total += doc.unreadCount;" +  
                "        }";  
        Query query = Query.query(Criteria.where("toUid").is(userId));  
        DBObject result = mongoTemplate.getCollection("gwb").group(new BasicDBObject("toUid", 1),   
                query.getQueryObject(),   
                new BasicDBObject("total", total),  
                reduce);  
          
        Map<String,BasicDBObject> map = result.toMap();  
        if(map.size() > 0){  
            BasicDBObject bdbo = map.get("0");  
            if(bdbo != null && bdbo.get("total") != null)  
                total = bdbo.getLong("total");  
        }  
      System.out.println(total);
    }  
//	
	
	@Test
	public void getNewMessageCount(){  
		String userId="gwb";
        Long total = 0l;  
        
        AggregationOperation match = Aggregation.match(Criteria.where("service").is("EFT").and("source").is("MARKUP"));
    
        System.out.println(total);
		Criteria criteria = Criteria.where("toUid").is(userId);
		mongoTemplate.group(criteria, "gwb", new GroupBy("toUid"), StateStats.class);
//		mongoTemplate.group(criteria, "gwb", new GroupBy("gwb"), StateStats.class);
   
        
        mongoTemplate.group(criteria, "collectionName", new GroupBy("gwb"), StateStats.class);
	
	}
}
