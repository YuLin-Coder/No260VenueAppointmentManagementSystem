package project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学习免费下 载
 * 供大家下载 学习参考
 */
@Service("db2")
public class MyBatiesPublic {
	@Autowired
	public PublicDao db;
	
	public List<Map> queryForList(String sql,Object[] list) {
		return db.queryForList(initSql(sql, list));
	}
	public List<Map> queryForList(String sql) {
		return db.queryForList(sql);
	}
	public Map queryForMap(String sql,Object[] list) {
		return db.queryForMap(initSql(sql, list));
	}
	public Map queryForMap(String sql) {
		return db.queryForMap(sql);
	}
	
	public int update(String sql,Object[] list) {
		return db.update(initSql(sql, list));
	}
	public int update(String sql) {
		return db.update(sql);
	}
	
	public String queryForString(String sql,Object[] list) {
		Map map = db.queryForMap(initSql(sql, list));
		System.out.println(map);
		if(map!=null&&map.size()>0){
			return map.get("stringval").toString();
		}
		return "";
	}
	public String queryForString(String sql) {
		Map map = db.queryForMap(sql);
		if(map!=null&&map.size()>0){
			return map.get("stringval").toString();
		}
		return "";
	}
	
	public int queryForInt(String sql,Object[] list) {
		Map map = db.queryForMap(initSql(sql, list));
		if(map!=null&&map.size()>0){
			return map.get("stringval")==null?null:Integer.parseInt(map.get("stringval").toString());
		}
		return 0;
	}
	public int queryForInt(String sql) {
		Map map = db.queryForMap(sql);
		if(map!=null&&map.size()>0){
			return map.get("stringval")==null?null:Integer.parseInt(map.get("stringval").toString());
		}
		return 0;
	}
	
	public String initSql(String sql, Object[] list) {

		if (sql.endsWith("?")) {
			String[] aa = sql.split("\\?");
			String res = "";
			for (int i = 0; i < aa.length; i++) {
				if (list[i] == null) {
					res += (aa[i] + "" + list[i] + "");
				} else {
					res += (aa[i] + "'" + list[i] + "'");
				}

			}
			return res;
		} else {
			String[] aa = sql.split("\\?");
			String res = "";
			for (int i = 0; i < list.length; i++) {
				if (list[i] == null) {
					res += (aa[i] + "" + list[i] + "");
				} else {
					res += (aa[i] + "'" + list[i] + "'");
				}

			}
			res += aa[aa.length - 1];
			return res;
		}

	}

	

}