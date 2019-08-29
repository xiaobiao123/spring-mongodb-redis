package other.grop;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import other.grop.ListGroup.GroupBy;

public class Test {
	
	private String name;
	
	private String sex;
	
	private String add;
	
    public static void main(String[] args) {
		List<Test> colls = new ArrayList<Test>();
		
		Test test = new Test();
		test.setSex("男");
		test.setName("卡卡");
		
		Test test1 = new Test();
		test1.setSex("男");
		test1.setName("CC");
		
		Test test2 = new Test();
		test2.setSex("男");
		test2.setName("梅西");
		
		Test test3 = new Test();
		test3.setSex("女");
		test3.setName("旺旺");
		
		Test test4 = new Test();
		test4.setSex("女");
		test4.setName("旺旺");
		
		colls.add(test);
		colls.add(test1);
		colls.add(test2);
		colls.add(test3);
		colls.add(test4);
		
		Map<String, List<Test>> map = ListGroup.group(colls, new GroupBy<String>() {
			 @Override
			 public String groupby(Object obj) {
				 Test d = (Test)obj ;
			     return d.getSex() ;    
			 }
		});
		
		for(Entry<String, List<Test>> m:  map.entrySet()){
			System.out.println(m.getKey());
			m.getValue();
		}
		
		
		System.out.println(JSON.toJSONString(map));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}
	
}
