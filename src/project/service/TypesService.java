package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.TypesDao;
import project.model.Types;
import project.util.PageTool;

@Service("TypesService")
public class TypesService extends BaseService{
	
	@Autowired
	public TypesDao typesDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param types
	 * @return
	 */
	public List<Types> getTypesList(HttpServletRequest request,Types types){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =typesDao.countAll(types);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		types.setBegin(begin);
		types.setPage_num(page_num);
		List<Types> list = typesDao.queryForList(types);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return typesDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Types getTypes(HttpServletRequest request,Long id){
		return typesDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Types types){
		return typesDao.insert(types);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param types
	 * @return
	 */
	public int update(HttpServletRequest request,Types types){
		return typesDao.update(types);
	}
}
