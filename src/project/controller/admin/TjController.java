
package project.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.controller.MyController;

@Controller("TjController")
@RequestMapping(value = "/admin/tj")
public class TjController extends MyController {
	

	@RequestMapping(value = "/tj1")
	public String tj1(Model model, HttpServletRequest request,String flag)throws Exception {
		
		String sql="select productName,count(1) counts from t_productlist where status in('预定中','完成') group by productName";
		List<Map> list = db.queryForList(sql);
		model.addAttribute("list", list);
		return "/admin/tj/tj1";
	}
	
	@RequestMapping(value = "/tj122")
	public String frame22(Model model, HttpServletRequest request,String flag)throws Exception {
		
		String sql="select date_format(insertDate,'%y-%m-%d') days,sum(allPrice) price from t_order group by date_format(insertDate,'%y-%m-%d')";
		List<Map> list = db.queryForList(sql);
		String aa="";
		String bb="";
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if(i==0){
					aa="'"+list.get(i).get("days")+"'";
					bb = list.get(i).get("price")+"";
				}else{
					aa+=",'"+list.get(i).get("days")+"'";
					bb +=","+ list.get(i).get("price")+"";
				}
			}
			
		}
		model.addAttribute("aa", aa);
		model.addAttribute("bb", bb);
		return "/admin/tj/tj1";
	}
	
	@RequestMapping(value = "/tj2")
	public String tj2(Model model, HttpServletRequest request,String flag)throws Exception {
		String sql="select date_format(insertDate,'%y-%m-%d') days,sum(fee) price from t_productlist where status='完成' group by date_format(insertDate,'%y-%m-%d')";
		List<Map> list = db.queryForList(sql);
		String aa="";
		String bb="";
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				if(i==0){
					aa="'"+list.get(i).get("days")+"'";
					bb = list.get(i).get("price")+"";
				}else{
					aa+=",'"+list.get(i).get("days")+"'";
					bb +=","+ list.get(i).get("price")+"";
				}
			}
			
		}
		model.addAttribute("aa", aa);
		model.addAttribute("bb", bb);
		
		
		return "/admin/tj/tj2";
	}
}
