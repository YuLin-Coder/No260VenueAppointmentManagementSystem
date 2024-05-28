package project.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//客户
public class Customer  extends BaseBean{
//主键
private Integer id;
public Integer getId() {return id;}
public void setId(Integer id) {this.id = id;}
//账号
private String username;
//密码
private String password;
//姓名
private String customerName;
//性别
private String sex;
//地址
private String address;
//手机
private String phone;
//账户
private Integer account;
//积分
private Integer jf;
public String getUsername() {return username;}
public void setUsername(String username) {this.username = username;}
public String getPassword() {return password;}
public void setPassword(String password) {this.password = password;}
public String getCustomerName() {return customerName;}
public void setCustomerName(String customerName) {this.customerName = customerName;}
public String getSex() {return sex;}
public void setSex(String sex) {this.sex = sex;}
public String getAddress() {return address;}
public void setAddress(String address) {this.address = address;}
public String getPhone() {return phone;}
public void setPhone(String phone) {this.phone = phone;}
public Integer getAccount() {return account;}
public void setAccount(Integer account) {this.account = account;}
public Integer getJf() {return jf;}
public void setJf(Integer jf) {this.jf = jf;}
}