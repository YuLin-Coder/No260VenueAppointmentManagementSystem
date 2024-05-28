package project.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学 习免费下载
 * 供大家 下载学习参考
 */
public class BatchSql {
	@SuppressWarnings("rawtypes")
	private List sqlList = new ArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addBatch(String sql, Object[] objects) {
		Map map = new HashMap();
		map.put("sql", sql);
		map.put("objects", objects);
		sqlList.add(map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addBatch(String sql) {
		Map map = new HashMap();
		map.put("sql", sql);
		map.put("objects", new Object[] {});
		sqlList.add(map);
	}

	@SuppressWarnings("rawtypes")
	public List getSqlList() {
		return sqlList;
	}
	
	@SuppressWarnings("rawtypes")
	public void clear() {
		sqlList = new ArrayList();
	}
}
