package project.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.util.RandomValidateCode;


/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学 习免费下载
 * 供大家下载学 习参考
 */
@Controller()
@RequestMapping(value = "/public")

public class PublicController extends MyController {
	
	
	/**
	 * 公共页面
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{page}")
	public String index(Model model,@PathVariable(value = "page") String page) throws Exception {
		return "/public/"+page;
	}
	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/image/validateImg")
	public void validateImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
