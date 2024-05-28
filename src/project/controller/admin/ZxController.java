
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
import project.model.Zx;
import project.service.ZxService;
import project.controller.MyController;

/**
*通知
* @author Administratorxxxx
* @date2019-03-02
*/
@Controller("zxController")
@RequestMapping(value = "/admin/zx")
public class ZxController extends MyController {
	@Autowired
	ZxService zxService;
	

/**
* 查询frame
*/
	@RequestMapping(value = "/frame")
	public String frame(Model model, HttpServletRequest request,String flag)throws Exception {
		return "/admin/zx/frame";
	}
	
/**
* 查询列表
*/
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request,String flag,String title,Zx zx)throws Exception {
		List<Zx> list = zxService.getZxList(request, zx);
		request.setAttribute("list", list);
		return "/admin/zx/list";
	}
	
/**
* 编辑保存（包含修改和添加）
*/
	@RequestMapping(value = "/editSave")
	public ResponseEntity<String> editSave(Model model,HttpServletRequest request,Long id,String flag,Zx zx
		) throws Exception{
		int result = 0;
		if(id!=null){
			result = zxService.update(request, zx);
		}else{
		
			result = zxService.save(request, zx);
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
		
		int result = zxService.deleteOne(request, id);
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
			Zx zx = zxService.getZx(request, id);
			model.addAttribute("map", zx);
		}String sql="";

		return "/admin/zx/edit";
	}
}
