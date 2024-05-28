
package project.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.controller.MyController;

@Controller("pinglunController")
@RequestMapping(value = "/admin/pinglun")
public class PinglunController extends MyController {
	

	@RequestMapping(value = "/frame")
	public String frame(Model model, HttpServletRequest request,String flag)throws Exception {
		return "/admin/pinglun/frame";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request,String flag)throws Exception {
		String sql="select a.*,(select title from t_wdxx b where a.wdxxId=b.id) wdxxTitle ,(select max(customerName) from t_customer b where a.customerId=b.id) customerName  from t_pinglun a where 1=1";

if(1==2){sql+="and customerId="+getCustomer(request).get("id") +" ";}
		sql+=" order by id desc";
		List list = db.queryForList(sql);
		request.setAttribute("list", list);
		return "/admin/pinglun/list";
	}
	
	@RequestMapping(value = "/editSave")
	public ResponseEntity<String> editSave(Model model,HttpServletRequest request,Long id,String flag
		,Integer wdxxId,Integer customerId,String content,String insertDate) throws Exception{
		int result = 0;
		if(id!=null){
			String sql="update t_pinglun set wdxxId=?,content=? where id=?";
			result = db.update(sql, new Object[]{wdxxId,content,id});
		}else{
			String sql="insert into t_pinglun(wdxxId,customerId,content,insertDate) values(?,?,?,now())";
			result = db.update(sql, new Object[]{wdxxId,getCustomer(request).get("id"),content});
		}
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
	}
	
	@RequestMapping(value = "/editDelete")
	public ResponseEntity<String> editDelete(Model model,HttpServletRequest request,Long id,String flag) throws Exception {
		
		String sql="delete from t_pinglun where id=?";
		int result = db.update(sql, new Object[]{id});
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
		
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request,Long id,String flag)throws Exception {
		if(id!=null){
			//修改
			String sql="select * from t_pinglun where id=?";
			Map map = db.queryForMap(sql,new Object[]{id});
			model.addAttribute("map", map);
		}String sql="";

 sql="select * from t_wdxx";
model.addAttribute("wdxxList", db.queryForList(sql));

		return "/admin/pinglun/edit";
	}
}
