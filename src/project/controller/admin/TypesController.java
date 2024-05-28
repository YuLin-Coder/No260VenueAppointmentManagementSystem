
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
import project.model.Types;
import project.service.TypesService;
import project.controller.MyController;

/**
*分类
* @author Administratorxxxx
* @date2019-03-02
*/
@Controller("typesController")
@RequestMapping(value = "/admin/types")
public class TypesController extends MyController {
	@Autowired
	TypesService typesService;
	

/**
* 查询frame
*/
	@RequestMapping(value = "/frame")
	public String frame(Model model, HttpServletRequest request,String flag)throws Exception {
		return "/admin/types/frame";
	}
	
/**
* 查询列表
*/
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request,String flag,String typesName,Types types)throws Exception {

		List<Types> list = typesService.getTypesList(request, types);
		request.setAttribute("list", list);
		return "/admin/types/list";
	}
	
/**
* 编辑保存（包含修改和添加）
*/
	@RequestMapping(value = "/editSave")
	public ResponseEntity<String> editSave(Model model,HttpServletRequest request,Long id,String flag,Types types
		) throws Exception{
		int result = 0;
		if(id!=null){
			result = typesService.update(request, types);
			
		}else{
		
			result = typesService.save(request, types);
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
		
		int result = typesService.deleteOne(request, id);
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
			Types types = typesService.getTypes(request, id);
			model.addAttribute("map", types);
		}String sql="";

		return "/admin/types/edit";
	}
}
