
package project.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxl.util.DateUtil;

import project.controller.MyController;
import project.model.Product;
import project.service.ProductService;

@Controller("productController")
@RequestMapping(value = "/admin/product")
public class ProductController extends MyController {
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/frame")
	public String frame(Model model, HttpServletRequest request,String flag)throws Exception {
		return "/admin/product/frame";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request,String flag,String productName,Product product)throws Exception {
//		String sql="select a.*,(select typesName from t_types b where a.typesId=b.id) typesName,(select max(name) from t_user c where c.id=a.userId) name  from t_product a where 1=1";
//		if(flag!=null&&"1,1".equals(flag)){
//		}
//
//		if(productName!=null&&!"".equals(productName)){
//			sql+=" and productName like '%"+productName+"%'";
//		}
//		sql+=" order by id desc";
//		List list = db.queryForList(sql);
//		request.setAttribute("list", list);
		
		
		
		List<Product> list = productService.getProductList(request, product);
		request.setAttribute("list", list);
		
		
		
		return "/admin/product/list";
	}
	
	@RequestMapping(value = "/editSave")
	public ResponseEntity<String> editSave(Model model,HttpServletRequest request,Long id,String flag
		,String productName,String productPic1,String productPic2,String productPic3,String productPic4,
		Integer price,Integer oldPrice,String content,Integer nums,String tjxj,String status,Integer typesId,Integer jf) throws Exception{
		int result = 0;
		int size=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 size = differentDays(new Date(), sdf.parse("2019-12-31"));
			System.out.println(size);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(id!=null){
			String sql="update t_product set productName=?,productPic1=?,productPic2=?,productPic3=?,productPic4=?,price=?," +
					"oldPrice=?,content=?,nums=?,tjxj=?,status=?,typesId=?,jf=? where id=?";
			result = db.update(sql, new Object[]{productName,productPic1,productPic2,productPic3,productPic4,price,oldPrice,content,nums,tjxj,status,typesId,jf,id});
			
			db.update("update t_productlist set fee=? and productName=? where status='待预定' and productId=?", new Object[]{price,productName,id});
			
		}else{
			String sql="insert into t_product(productName,productPic1,productPic2,productPic3,productPic4,price," +
					"oldPrice,content,nums,tjxj,status,typesId,jf,userId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			result = db.update(sql, new Object[]{productName,productPic1,productPic2,productPic3,productPic4,price,
					oldPrice,content,nums,tjxj,status,typesId,jf,null});
			Map map = db.queryForMap("select * from t_product order by id desc limit 1");
			id = Long.valueOf( map.get("id").toString());
			String[] bbb = new String[]{"09:00~10:00","10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"};
			String bb="insert into t_productlist(productId,orderDate,zc,sjd,status,fee,productName) values(?,?,?,?,?,?,?)";
			for (int i = 0; i < size; i++) {
				Date date = new Date();
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, i);
				date = calendar.getTime();
				for (int j = 0; j < bbb.length; j++) {
					db.update(bb, new Object[]{id,sdf.format(date),aaa(date),bbb[j],"待预定",price,productName});
				}
			}
		}
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
	}
	
	
	public String aaa(Date date){
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
		
	}
	
	public static void main(String[] args) {
		
	}
	
	
	 public static int differentDays(Date date1,Date date2)
	    {
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	       int day1= cal1.get(Calendar.DAY_OF_YEAR);
	        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
	        
	        int year1 = cal1.get(Calendar.YEAR);
	        int year2 = cal2.get(Calendar.YEAR);
	        if(year1 != year2)   //同一年
	        {
	            int timeDistance = 0 ;
	            for(int i = year1 ; i < year2 ; i ++)
	            {
	                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
	                {
	                    timeDistance += 366;
	                }
	                else    //不是闰年
	                {
	                    timeDistance += 365;
	                }
	            }
	            
	            return timeDistance + (day2-day1) ;
	        }
	        else    //不同年
	        {
	            return day2-day1;
	        }
	    }
	
	@RequestMapping(value = "/editDelete")
	public ResponseEntity<String> editDelete(Model model,HttpServletRequest request,Long id,String flag) throws Exception {
		
		String sql="delete from t_product where id=?";
		int result = db.update(sql, new Object[]{id});
		if(result==1){
			return renderData(true,"操作成功",null);
		}else{
			return renderData(false,"操作失败",null);
		}
		
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request,Long id,String flag)throws Exception {
		if(id!=null){
			//修改
			String sql="select * from t_product where id=?";
			Map map = db.queryForMap(sql,new Object[]{id});
			model.addAttribute("map", map);
		}String sql="";

 sql="select * from t_types";
model.addAttribute("typesList", db.queryForList(sql));

		return "/admin/product/edit";
	}
}
