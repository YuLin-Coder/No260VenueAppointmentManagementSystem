package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.CustomerDao;
import project.model.Customer;
import project.util.PageTool;

@Service("CustomerService")
public class CustomerService extends BaseService{
	
	@Autowired
	public CustomerDao customerDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param customer
	 * @return
	 */
	public List<Customer> getCustomerList(HttpServletRequest request,Customer customer){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =customerDao.countAll(customer);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		customer.setBegin(begin);
		customer.setPage_num(page_num);
		List<Customer> list = customerDao.queryForList(customer);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return customerDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Customer getCustomer(HttpServletRequest request,Long id){
		return customerDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Customer customer){
		return customerDao.insert(customer);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param customer
	 * @return
	 */
	public int update(HttpServletRequest request,Customer customer){
		return customerDao.update(customer);
	}
}
