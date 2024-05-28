package project.util;

import java.util.Map;



/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学 习免费下载
 * 供大 家下载学习参考
 */
public class StringHelper {

	/**
	 * 
	 * @param sql
	 * @param params
	 */
	@SuppressWarnings({ "unused", "static-access" })
	public static String getSql(String sql, Object[] params) {
		StringHelper str = new StringHelper();
		int idx = -1;
		int i = 0;
		String tmp = sql;
		while ((idx = tmp.indexOf("?")) > -1 && i < params.length) {
			tmp = tmp.replaceFirst("\\?", "'"
					+ str.toSql(str.notEmpty(String.valueOf(params[i++]) )) + "'");
		}
		return tmp;

	}

	public static String notEmpty(Object value) {
		if (value == null) {
			value = "";
		}
		return String.valueOf(value).trim();
	}

	public static String toSql(String str) {
		String sql = new String(str);
		return Replace(sql, "'", "''");
	}
	
	/**
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String Replace(String source, String oldString, String newString) {
			StringBuffer output = new StringBuffer();

	        int lengthOfSource = source.length();   // 源锟街凤拷锟斤拷
	        int lengthOfOld = oldString.length();   // 锟斤拷锟芥换锟街凤拷锟斤拷

	        int posStart = 0;   // 锟斤拷始锟斤拷锟斤拷位锟斤拷
	        int pos;            // 锟斤拷锟斤拷锟斤拷锟街凤拷锟轿伙拷锟�

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
	
    @SuppressWarnings("rawtypes")
	public static String get(Map map,String keyName)
    {
    	return notEmpty(map.get(keyName));
    }
    
    public static String arrayToString(String[] array,String split){
    	if(array==null){
    		return "";
    	}
    	if(array.length>0){
    		String result = "";
    		for (int i = 0; i < array.length; i++) {
    			if(i==0){
    				result+=array[i];
    			}else{
    				result+=split+array[i];
    			}
			}
    		return result;
    	}else{
    		return "";
    	}
    }
}
