package grop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 集合分组测试
 * 
 */
public class GroupTest {

	/**
	 * 分組依據接口，用于集合分組時，獲取分組依據
	 * 
	 * @author ZhangLiKun
	 * @title GroupBy
	 * @date 2013-4-23
	 */
	public interface GroupBy<T> {
		T groupby(Object obj);
	}

	/**
	 *
	 * @param colls
	 * @param gb
	 * @return
	 */
	public static final <T extends Comparable<T>, D> Map<T, List<D>> group(Collection<D> colls, GroupBy<T> gb) {
		if (colls == null || colls.isEmpty()) {
			System.out.println("分组集合不能为空!");
			return null;
		}
		if (gb == null) {
			System.out.println("分组依据接口不能为null!");
			return null;
		}
		Iterator<D> iter = colls.iterator();
		Map<T, List<D>> map = new HashMap<T, List<D>>();
		while (iter.hasNext()) {
			D d = iter.next();
			T t = gb.groupby(d);
			if (map.containsKey(t)) {
				map.get(t).add(d);
			} else {
				List<D> list = new ArrayList<D>();
				list.add(d);
				map.put(t, list);
			}
		}
		return map;
	}

	@Test
	public void test() {
		// 准备一个集合
		final int loop = 1000;
		List<Data> list = new ArrayList<Data>(); // size=8 * loop
		for (int i = 0; i < loop; i++) {
			list.add(new Data().setId(1L).setCourseId(200010L).setContent("AAA"));
			list.add(new Data().setId(2L).setCourseId(200010L).setContent("BBB"));
			list.add(new Data().setId(3L).setCourseId(200011L).setContent("CCC"));
			list.add(new Data().setId(4L).setCourseId(200011L).setContent("DDD"));
			list.add(new Data().setId(5L).setCourseId(200010L).setContent("EEE"));
			list.add(new Data().setId(6L).setCourseId(200011L).setContent("FFF"));
			list.add(new Data().setId(7L).setCourseId(200010L).setContent("GGG"));
			list.add(new Data().setId(8L).setCourseId(200012L).setContent("HHH"));
		}

		// 进行分组
		Map<Long, List<Data>> map = group(list, new GroupBy<Long>() {
			@Override
			public Long groupby(Object obj) {
				Data d = (Data) obj;
				return d.getCourseId(); // 分组依据为课程ID
			}
		});

		assertEquals(3, map.size());
		assertEquals(4 * loop, map.get(200010L).size());
		assertEquals(3 * loop, map.get(200011L).size());
		assertEquals(1 * loop, map.get(200012L).size());
		assertEquals("HHH", map.get(200012L).get(0).getContent());

		// 长度为8 * 1000 * 1000的集合测试用时：6481毫秒!

	}

	private long time;

	@Before
	public void setup() {
		time = System.currentTimeMillis();
	}

	@After
	public void teardown() {
		System.out.println(String.format("程序执行：%d毫秒!", System.currentTimeMillis() - time));
	}

}