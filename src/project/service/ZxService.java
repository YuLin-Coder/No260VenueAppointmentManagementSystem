package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.ZxDao;
import project.model.Zx;
import project.util.PageTool;

@Service("ZxService")
public class ZxService extends BaseService{
	
	@Autowired
	public ZxDao zxDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param zx
	 * @return
	 */
	public List<Zx> getZxList(HttpServletRequest request,Zx zx){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =zxDao.countAll(zx);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		zx.setBegin(begin);
		zx.setPage_num(page_num);
		List<Zx> list = zxDao.queryForList(zx);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return zxDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Zx getZx(HttpServletRequest request,Long id){
		return zxDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Zx zx){
		return zxDao.insert(zx);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param zx
	 * @return
	 */
	public int update(HttpServletRequest request,Zx zx){
		return zxDao.update(zx);
	}
}
