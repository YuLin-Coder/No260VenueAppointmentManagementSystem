package project.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//场地预定
public class Productlist  extends BaseBean{
//主键
private Integer id;
public Integer getId() {return id;}
public void setId(Integer id) {this.id = id;}
//场地
private Integer productId;
//订单日期
private String orderDate;
//周
private String zc;
//时间段
private String sjd;
//状态
private String status;
//用户
private Integer customerId;
//下单日期
private Date insertDate;
//价格
private Integer fee;
//订单编号
private String orderNum;
//场地名称
private String productName;
public Integer getProductId() {return productId;}
public void setProductId(Integer productId) {this.productId = productId;}
public String getOrderDate() {return orderDate;}
public void setOrderDate(String orderDate) {this.orderDate = orderDate;}
public String getZc() {return zc;}
public void setZc(String zc) {this.zc = zc;}
public String getSjd() {return sjd;}
public void setSjd(String sjd) {this.sjd = sjd;}
public String getStatus() {return status;}
public void setStatus(String status) {this.status = status;}
public Integer getCustomerId() {return customerId;}
public void setCustomerId(Integer customerId) {this.customerId = customerId;}
public Date getInsertDate() {return insertDate;}
public void setInsertDate(Date insertDate) {this.insertDate = insertDate;}
public Integer getFee() {return fee;}
public void setFee(Integer fee) {this.fee = fee;}
public String getOrderNum() {return orderNum;}
public void setOrderNum(String orderNum) {this.orderNum = orderNum;}
public String getProductName() {return productName;}
public void setProductName(String productName) {this.productName = productName;}
}