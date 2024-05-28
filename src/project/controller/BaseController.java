package project.controller;


import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import project.util.JacksonJsonUtil;




public class BaseController {
	public static final Long EXPIRES_IN = 1000 * 3600 * 24 * 1L;// 1天



	/**
	 * 返回服务器地址 like http://192.168.1.1:8441/UUBean/
	 */
	public String getHostUrl(HttpServletRequest request) {
		String hostName = request.getServerName();
		Integer hostPort = request.getServerPort();
		String path = request.getContextPath();

		if (hostPort == 80) {
			return "http://" + hostName + path + "/";
		} else {
			return "http://" + hostName + ":" + hostPort + path + "/";
		}
	}

	/***
	 * 获取当前的website路径 String
	 */
	public static String getWebSite(HttpServletRequest request) {
		String returnUrl = request.getScheme() + "://"
				+ request.getServerName();

		if (request.getServerPort() != 80) {
			returnUrl += ":" + request.getServerPort();
		}

		returnUrl += request.getContextPath();

		return returnUrl;
	}



	/**
	 * 初始化HTTP头.
	 * 
	 * @return HttpHeaders
	 */
	public HttpHeaders initHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html",
				Charset.forName("utf-8"));
		headers.setContentType(mediaType);
		return headers;
	}

	/**
	 * 返回 信息数据
	 * 
	 * @param status
	 * @param msg
	 * @return
	 */
	public ResponseEntity<String> renderMsg(Boolean status, String msg) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		String str = "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"}";
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(str,
				initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 返回obj数据
	 * 
	 * @param status
	 * @param msg
	 * @param obj
	 * @return
	 */
	public ResponseEntity<String> renderData(Boolean status, String msg,
			Object obj) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(obj) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}


	/***
	 * 获取IP（如果是多级代理，则得到的是一串IP值）
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip != null && ip.length() > 0) {
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if (!"unknown".equalsIgnoreCase(ips[i])) {
					ip = ips[i];
					break;
				}
			}
		}

		return ip;
	}

	
}
