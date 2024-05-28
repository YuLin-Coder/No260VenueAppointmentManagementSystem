
package project.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import project.controller.MyController;
import project.model.Productlist;
import project.service.ProductlistService;
import project.controller.MyController;

/**
*场地预定
* @author Administratorxxxx
* @date2019-03-02
*/
@Controller("productlistController")
@RequestMapping(value = "/admin/productlist")
public class ProductlistController extends MyController {
	@Autowired
	ProductlistService productlistService;
	

/**
* 查询frame
*/
	@RequestMapping(value = "/frame")
	public String frame(Model model, HttpServletRequest request,String flag)throws Exception {
		return "/admin/productlist/frame";
	}
	
/**
* 查询列表
*/
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request,String flag,String orderDate,String zc,String sjd,String status,String orderNum,String productName,Productlist productlist)throws Exception {
		List<Productlist> list = productlistService.getProductlistList(request, productlist);
		request.setAttribute("list", list);
		return "/admin/productlist/list";
	}
	
	@RequestMapping(value = "/updateColumnstatus")
	public ResponseEntity<String> updateColumnstatus(Model model,HttpServletRequest request,Long id,String status,String flag) throws Exception {
		//已退订
		System.out.println(status);
		if("已退订".equals(status)){
			System.out.println(id);
			db.update("update t_productlist set status='待预定'   where id=?", new Object[]{id});
		}else{
			db.update("update t_productlist set status=?   where id=?", new Object[]{status,id});
		}
		return renderData(true,"操作成功",null);
		
	}
	
/**
* 编辑保存（包含修改和添加）
*/
	@RequestMapping(value = "/editSave")
	public ResponseEntity<String> editSave(Model model,HttpServletRequest request,Long id,String flag,Productlist productlist
		) throws Exception{
		int result = 0;
		if(id!=null){
			result = productlistService.update(request, productlist);
			
		}else{
				productlist.setInsertDate(new Date());

			result = productlistService.save(request, productlist);
		}
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
	}
	
/**
* 删除信息
*/
	@RequestMapping(value = "/editDelete")
	public ResponseEntity<String> editDelete(Model model,HttpServletRequest request,Long id,String flag) throws Exception {
		
		int result = productlistService.deleteOne(request, id);
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
		
	}
	
/**
* 跳转到编辑页面
*/
	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request,Long id,String flag)throws Exception {
		if(id!=null){
			//修改
			Productlist productlist = productlistService.getProductlist(request, id);
			model.addAttribute("map", productlist);
		}String sql="";

		return "/admin/productlist/edit";
	}
}
