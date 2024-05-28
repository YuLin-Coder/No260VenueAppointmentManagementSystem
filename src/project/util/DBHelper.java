package project.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文 学习免费下载
 * 供大家下载 学习参考
 */
public class DBHelper {
	public TransactionTemplate transactionTemplate;
	public JdbcTemplate jdbcTemplate;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static Logger logger = Logger.getLogger(DBHelper.class);
	
	
	@SuppressWarnings("unused")
	public String getSql(String sql, Object[] params) {
		int idx = -1;
		int i = 0;
		String tmp = sql;
		while ((idx = tmp.indexOf("?")) > -1 && i < params.length) {
			tmp = tmp.replaceFirst("\\?", "'"
					+ toSql(notEmpty((String) params[i++])) + "'");
		}
		return tmp;

	}

	public String notEmpty(Object value) {
		if (value == null) {
			value = "";
		}
		return String.valueOf(value).trim();
	}

	public String toSql(String str) {
		String sql = new String(str);
		return Replace(sql, "'", "''");
	}

	public String Replace(String source, String oldString, String newString) {
		StringBuffer output = new StringBuffer();
		int lengthOfSource = source.length();
		int lengthOfOld = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengthOfOld;
		}
		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/**
	 * 批量保存
	 * 
	 * @param sql
	 * @return
	 */
	public int batchUpdate(final BatchSql batchSql) {
		int exc = 1;
		if (batchSql == null) {
			exc = 0;
		}
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@SuppressWarnings("rawtypes")
				public void doInTransactionWithoutResult(
						TransactionStatus status) {
					List sqlList = batchSql.getSqlList();
					for (int i = 0; i < sqlList.size(); i++) {
						Map sqlMap = (Map) sqlList.get(i);
						String sql = (String) sqlMap.get("sql");
						Object[] objects = (Object[]) sqlMap.get("objects");
						jdbcTemplate.update(sql, objects);
					}
				}
			});
		} catch (Exception e) {
			exc = 0;
			logger.error(e.toString());
		}
		return exc;
	}

	/**
	 * 单个保存
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public int update(String sql, Object[] objects) {
		int exc = 1;
		try {
			this.jdbcTemplate.update(sql, objects);
		} catch (Exception e) {
			exc = 0;
			logger.error(e);
			logger.error(getSql(sql, objects));
		}
		return exc;
	}

	public int update(String sql) {
		int exc = 1;
		try {
			this.jdbcTemplate.update(sql);
		} catch (Exception e) {
			exc = 0;
			logger.error(e);
			logger.error(sql);
		}
		return exc;
	}

	@SuppressWarnings("rawtypes")
	public Map queryForMap(String sql) {
		Map map = null;
		try {
			map = jdbcTemplate.queryForMap(sql);
		} catch (Exception e) {
			logger.error(e);
			logger.error(sql);
		}
		if (map == null)
			map = new HashMap();
		return map;
	}

	@SuppressWarnings("rawtypes")
	public Map queryForMap(String sql, Object[] objects) {
		Map map = null;
		try {
			map = jdbcTemplate.queryForMap(sql, objects);
		} catch (Exception e) {
			logger.error(e);
			logger.error(getSql(sql, objects));
		}
		if (map == null)
			map = new HashMap();
		return map;
	}

	public String queryForString(String sql) {
		try {
			return notEmpty(jdbcTemplate
					.queryForObject(sql, null, String.class));
		} catch (Exception e) {
			logger.error(e);
			logger.error(sql);
			return "";
		}
	}

	public String queryForString(String sql, Object[] args) {
		try {
			return notEmpty(jdbcTemplate
					.queryForObject(sql, args, String.class));
		} catch (Exception e) {
			logger.error(e);
			logger.error(getSql(sql, args));
			return "";
		}
	}

	@SuppressWarnings("rawtypes")
	public List queryForList(String sql) {
		List list = null;
		try {
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			logger.error(e);
			logger.error(sql);
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List queryForList(String sql, Object[] objects) {
		List list = null;
		try {
			list = jdbcTemplate.queryForList(sql, objects);
		} catch (Exception e) {
			logger.error(e);
			logger.error(getSql(sql, objects));
		}
		if (list == null)
			list = new ArrayList();
		return list;
	}

	public int queryForInt(String sql, Object[] objects) {
		int exc = -1;
		try {
			exc = jdbcTemplate.queryForInt(sql, objects);
		} catch (Exception e) {
			logger.error(e);
			logger.error(getSql(sql, objects));
		}
		return exc;
	}

	public int queryForInt(String sql) {
		int exc = -1;
		try {
			exc = jdbcTemplate.queryForInt(sql);
		} catch (Exception e) {
			logger.error(e);
			logger.error(sql);
		}
		return exc;
	}
	
	
	@SuppressWarnings("rawtypes")
	public List queryListForPage22(String sql, HttpServletRequest request) {
		//pageNum是序列 1,2,3,4， pageSize 是每页显示数量 
		Integer pageSize = StringUtils.isBlank(request.getParameter("pageSize"))?10:Integer.parseInt(request.getParameter("pageSize"));
		Integer pageNum = StringUtils.isBlank(request.getParameter("pageNum"))?1:Integer.parseInt(request.getParameter("pageNum"));
		int begin = pageSize*(pageNum-1);
		sql=sql+" limit "+begin+","+pageSize;
		return this.queryForList(sql);
	}
	
	@SuppressWarnings("rawtypes")
	public List queryListForPage(String sql, HttpServletRequest request) {
		Integer page_num=5;
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size = this.queryForInt("select count(1) from ("+sql+") a");
		int begin = page_num*(pageIndex-1);
		sql=sql+" limit "+begin+","+page_num;
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		return this.queryForList(sql);
	}
	
	/**
	 * 已经明确object对象数量的用这个
	 * @param sql
	 * @param request
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryListForPage(String sql, HttpServletRequest request, Object[] objects) {
		Integer page_num=5;
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size = this.queryForInt("select count(1) from ("+sql+") a");
		int begin = page_num*(pageIndex-1);
		sql=sql+" limit "+begin+","+page_num;
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		return this.queryForList(sql,objects);
	}
	
	/**
	 * 未明确的，传入一个list然后重新排版
	 * @param sql
	 * @param request
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryListForPage(String sql, HttpServletRequest request, List<Object> list) {
		Integer page_num=5;
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size = this.queryForInt("select count(1) from ("+sql+") a");
		int begin = page_num*(pageIndex-1);
		sql=sql+" limit "+begin+","+page_num;
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		
		if(list!=null&&list.size()>0){
			Object[] o = new Object[list.size()];
			for (int i = 0; i < list.size(); i++) {
				o[i]=list.get(i);
			}
			return this.queryForList(sql,o);
		}else{
			return this.queryForList(sql);
		}
	}
	
	public String getOracleSequence() {
		String sql="select filem_s_index.nextval from dual";
		try {
			return notEmpty(jdbcTemplate
					.queryForObject(sql, null, String.class));
		} catch (Exception e) {
			logger.error(e);
			logger.error(sql);
			return "";
		}
	}
	
	public String geturl(HttpServletRequest request){
		
		String strBackUrl = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + request.getContextPath()      //项目名称  
                + request.getServletPath();      //请求页面或其他地址  
		Enumeration enu=request.getParameterNames();
		String out = "";
		while(enu.hasMoreElements()){
			String paraName=(String)enu.nextElement();  
			if(!"offset".equals(paraName)){
				out+="&"+paraName+"="+request.getParameter(paraName);
			}
		}
		if(out!=null&&!"".equals(out)){
			strBackUrl+="?"+out.substring(1,out.length());
		}
		return strBackUrl;
	}
	
	public String getRequestUrl(HttpServletRequest request)
	{
		String url =request.getRequestURI();
		String path = request.getContextPath();
		if (StringUtils.isNotEmpty(path))
		{
			return url.substring(path.length());
		}
		return url;
	}
	
}
