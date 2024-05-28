package project.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//建议
public class Contact  extends BaseBean{
//主键
private Integer id;
public Integer getId() {return id;}
public void setId(Integer id) {this.id = id;}
//用户
private Long customerId;
//联系方式
private String phone;
//内容
private String content;
//日期
private Date insertDate;
public Long getCustomerId() {return customerId;}
public void setCustomerId(Long customerId) {this.customerId = customerId;}
public String getPhone() {return phone;}
public void setPhone(String phone) {this.phone = phone;}
public String getContent() {return content;}
public void setContent(String content) {this.content = content;}
public Date getInsertDate() {return insertDate;}
public void setInsertDate(Date insertDate) {this.insertDate = insertDate;}
}