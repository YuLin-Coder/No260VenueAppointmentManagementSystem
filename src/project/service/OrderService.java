package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.OrderDao;
import project.model.Order;
import project.util.PageTool;

@Service("OrderService")
public class OrderService extends BaseService{
	
	@Autowired
	public OrderDao orderDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param order
	 * @return
	 */
	public List<Order> getOrderList(HttpServletRequest request,Order order){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =orderDao.countAll(order);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		order.setBegin(begin);
		order.setPage_num(page_num);
		List<Order> list = orderDao.queryForList(order);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return orderDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Order getOrder(HttpServletRequest request,Long id){
		return orderDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Order order){
		return orderDao.insert(order);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param order
	 * @return
	 */
	public int update(HttpServletRequest request,Order order){
		return orderDao.update(order);
	}
}
