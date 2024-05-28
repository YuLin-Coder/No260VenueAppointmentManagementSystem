package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.ProductDao;
import project.model.Product;
import project.util.PageTool;

@Service("ProductService")
public class ProductService extends BaseService{
	
	@Autowired
	public ProductDao productDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param product
	 * @return
	 */
	public List<Product> getProductList(HttpServletRequest request,Product product){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =productDao.countAll(product);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		product.setBegin(begin);
		product.setPage_num(page_num);
		List<Product> list = productDao.queryForList(product);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return productDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Product getProduct(HttpServletRequest request,Long id){
		return productDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Product product){
		return productDao.insert(product);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param product
	 * @return
	 */
	public int update(HttpServletRequest request,Product product){
		return productDao.update(product);
	}
}
