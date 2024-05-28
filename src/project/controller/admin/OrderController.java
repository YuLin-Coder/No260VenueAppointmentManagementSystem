
package project.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.controller.MyController;

@Controller("orderController")
@RequestMapping(value = "/admin/order")
public class OrderController extends MyController {
	

	@RequestMapping(value = "/frame")
	public String frame(Model model, HttpServletRequest request,String flag)throws Exception {
		return "/admin/order/frame";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request,String flag)throws Exception {
		String sql="select a.*,(select max(customerName) from t_customer b where a.customerId=b.id) name  from t_productlist a where 1=1 and status='预定中' ";
		sql+=" order by id desc";
		List list = db.queryForList(sql);
		request.setAttribute("list", list);
		return "/admin/order/list";
	}
	
	@RequestMapping(value = "/editSave")
	public ResponseEntity<String> editSave(Model model,HttpServletRequest request,Long id
		,Integer customerId,String productDetail,String allPrice,String status,String flag) throws Exception{
		int result = 0;
		if(id!=null){
			String sql="update t_order set productDetail=?,allPrice=? where id=?";
			result = db.update(sql, new Object[]{productDetail,allPrice,id});
		}else{
			String sql="insert into t_order(customerId,productDetail,allPrice,status) values(?,?,?,?)";
			result = db.update(sql, new Object[]{getCustomer(request).get("id"),productDetail,allPrice,"等待处理"});
		}
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
	}
	
	@RequestMapping(value = "/editDelete")
	public ResponseEntity<String> editDelete(Model model,HttpServletRequest request,Long id,String flag) throws Exception {
		
		String sql="delete from t_order where id=?";
		int result = db.update(sql, new Object[]{id});
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
		
	}
	@RequestMapping(value = "/updateColumncustomerId")
	public ResponseEntity<String> updateColumncustomerId(Model model,HttpServletRequest request,Long id,String customerId) throws Exception {
		
		String sql="update t_order set customerId=?   where id=?";
		int result = db.update(sql, new Object[]{customerId,id});
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
		
	}
	@RequestMapping(value = "/updateColumnstatus")
	public ResponseEntity<String> updateColumnstatus(Model model,HttpServletRequest request,Long id,String status,String flag) throws Exception {
		//已退订
		if("已退订".equals(status)){
			db.update("update t_productlist set status='预定中'   where id=?", new Object[]{status,id});
		}else{
			db.update("update t_productlist set status=?   where id=?", new Object[]{status,id});
		}
		return renderData(true,"操作成功",null);
		
	}
	
	@RequestMapping(value = "/updateColumnstatuswl")
	public ResponseEntity<String> updateColumnstatuswl(Model model,HttpServletRequest request,Long id,String pl,String flag) throws Exception {
		
		String sql="update t_order set pl=CONCAT(case when pl is null then '' else pl end,'"+pl+"<br/>')   where id=?";
		System.out.println(sql+"=="+id);
		int result = db.update(sql, new Object[]{id});
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
		
	}
	
	
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request,Long id)throws Exception {
		if(id!=null){
			//修改
			String sql="select * from t_order where id=?";
			Map map = db.queryForMap(sql,new Object[]{id});
			model.addAttribute("map", map);
		}String sql="";

		return "/admin/order/edit";
	}
}
