package project.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.controller.MyController;
import project.util.StringHelper;


@Controller("customerLoginController")
@RequestMapping(value = "/customerLogin")
public class CustomerLoginController extends MyController {
	

	@RequestMapping(value = "/login")
	public String index(Model model, HttpServletRequest request)throws Exception {
		return "/customer/login";
	}
	
	@RequestMapping(value = "/save")
	public ResponseEntity<String> loginSave(Model model,HttpServletRequest request,String username,String password) throws Exception {
		String sql="select * from t_customer where username=?";
		List<Map> list = db.queryForList(sql, new Object[]{username});
		String result="1";
		if(list!=null&&list.size()>0){
			Map map = list.get(0);
			if(StringHelper.get(map, "password").equals(password)){
				request.getSession().setMaxInactiveInterval(60*60*24);
				request.getSession().setAttribute("customerBean", map);
				result="1";
			}else{
				result="0";
			}
		}else{
			result="0";
		}
		return renderData(true,result,null);
	}

	@RequestMapping(value = "/registerSave")
	public ResponseEntity<String> mineSave(Model model,HttpServletRequest request,Long id
		,String username,String password,String customerName,String sex,String address,String mobile) throws Exception{
		int result = 0;
			String sql="insert into t_customer(username,password,customerName,sex,address,mobile) values(?,?,?,?,?,?)";
			result = db.update(sql, new Object[]{username,password,customerName,sex,address,mobile});
		sql="select * from t_customer order by id desc limit 1";
		List<Map> list = db.queryForList(sql);
		request.getSession().setMaxInactiveInterval(60*60*24);
		request.getSession().setAttribute("customerBean", list.get(0));

			return renderData(true,"操作成功",null);
	}
		
	@RequestMapping(value = "/out")
	public String out(Model model, HttpServletRequest request)throws Exception {
		request.getSession().removeAttribute("customerBean");
		return "redirect:/adminLogin/login.html";
	}
	
}
