
package project.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.controller.MyController;

@Controller("customerController")
@RequestMapping(value = "/customer")
public class CustomerController extends MyController {
	

	@RequestMapping(value = "/index")
	public String frame(Model model, HttpServletRequest request)throws Exception {
		return "/customer/index";
	}
	
	@RequestMapping(value = "/main")
	public String main(Model model, HttpServletRequest request)throws Exception {
		return "/customer/main";
	}
	
	
	@RequestMapping(value = "/password")
	public String password(Model model, HttpServletRequest request)throws Exception {
		return "/customer/password";
	}
	
	
	@RequestMapping(value = "/changePassword")
	public ResponseEntity<String> loginSave(Model model,HttpServletRequest request,String oldPassword,String newPassword) throws Exception {
		Map customer = getCustomer(request);
		if(oldPassword.equals(customer.get("password").toString())){
			String sql="update t_customer set password=? where id=?";
			db.update(sql, new Object[]{newPassword,customer.get("id")});
			return renderData(true,"1",null);
		}else{
			return renderData(false,"1",null);
		}
	}
	@RequestMapping(value = "/mine")
	public String mine(Model model, HttpServletRequest request)throws Exception {
Map user =getCustomer(request);Map map = db.queryForMap("select * from t_customer where id=?",new Object[]{user.get("id")});model.addAttribute("map", map);		return "/customer/mine";
	}
	
	

	@RequestMapping(value = "/mineSave")
	public ResponseEntity<String> mineSave(Model model,HttpServletRequest request,Long id
		,String username,String password,String customerName,String sex,String address,String mobile) throws Exception{
		int result = 0;
			String sql="update t_customer set customerName=?,sex=?,address=?,mobile=? where id=?";
			result = db.update(sql, new Object[]{customerName,sex,address,mobile,id});
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
	}
	}
