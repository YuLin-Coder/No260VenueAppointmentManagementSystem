package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.LbtDao;
import project.model.Lbt;
import project.util.PageTool;

@Service("LbtService")
public class LbtService extends BaseService{
	
	@Autowired
	public LbtDao lbtDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param lbt
	 * @return
	 */
	public List<Lbt> getLbtList(HttpServletRequest request,Lbt lbt){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =lbtDao.countAll(lbt);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		lbt.setBegin(begin);
		lbt.setPage_num(page_num);
		List<Lbt> list = lbtDao.queryForList(lbt);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return lbtDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Lbt getLbt(HttpServletRequest request,Long id){
		return lbtDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Lbt lbt){
		return lbtDao.insert(lbt);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param lbt
	 * @return
	 */
	public int update(HttpServletRequest request,Lbt lbt){
		return lbtDao.update(lbt);
	}
}
