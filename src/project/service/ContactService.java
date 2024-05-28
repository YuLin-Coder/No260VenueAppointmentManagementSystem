package project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.dao.ContactDao;
import project.model.Contact;
import project.util.PageTool;

@Service("ContactService")
public class ContactService extends BaseService{
	
	@Autowired
	public ContactDao contactDao;
	
	/**
	 * 分页查询
	 * @param request
	 * @param contact
	 * @return
	 */
	public List<Contact> getContactList(HttpServletRequest request,Contact contact){
		Integer page_num=5;
		String page_nums = request.getParameter("page_num");
		if(page_nums!=null&&!"".equals(page_nums)){
			page_num =Integer.parseInt(page_nums);
		}
		int pageIndex = request.getParameter("offset")==null?1:Integer.parseInt(request.getParameter("offset"));
		int size =contactDao.countAll(contact);
		int begin = page_num*(pageIndex-1);
		PageTool page = new PageTool(pageIndex, page_num,size);
		page.setHref(geturl(request));
		request.setAttribute("page", page);
		contact.setBegin(begin);
		contact.setPage_num(page_num);
		List<Contact> list = contactDao.queryForList(contact);
		return list;
	}
	
	/**
	 * 删除一条记录
	 * @param request
	 * @param id
	 * @return
	 */
	public int deleteOne(HttpServletRequest request,Long id){
		return contactDao.delete(id);
	}
	
	/**
	 * 根据id获取记录
	 * @param request
	 * @param id
	 * @return
	 */
	public Contact getContact(HttpServletRequest request,Long id){
		return contactDao.getById(id);
	}
	
	/**
	 * 新增一条记录
	 * @param request
	 * @param book
	 * @return
	 */
	public int save(HttpServletRequest request,Contact contact){
		return contactDao.insert(contact);
	}
	
	/**
	 * 更新记录
	 * @param request
	 * @param contact
	 * @return
	 */
	public int update(HttpServletRequest request,Contact contact){
		return contactDao.update(contact);
	}
}
