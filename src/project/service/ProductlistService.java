package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.ProductlistDao;
import project.model.Productlist;
import project.util.PageTool;

@Service("ProductlistService")
public class ProductlistService extends BaseService{
	
	@Autowired
	public ProductlistDao productlistDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param productlist
	 * @return
	 */
	public List<Productlist> getProductlistList(HttpServletRequest request,Productlist productlist){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =productlistDao.countAll(productlist);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		productlist.setBegin(begin);
		productlist.setPage_num(page_num);
		List<Productlist> list = productlistDao.queryForList(productlist);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return productlistDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Productlist getProductlist(HttpServletRequest request,Long id){
		return productlistDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Productlist productlist){
		return productlistDao.insert(productlist);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param productlist
	 * @return
	 */
	public int update(HttpServletRequest request,Productlist productlist){
		return productlistDao.update(productlist);
	}
}
