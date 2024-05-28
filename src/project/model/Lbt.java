package project.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//轮播图
public class Lbt  extends BaseBean{
//主键
private Integer id;
public Integer getId() {return id;}
public void setId(Integer id) {this.id = id;}
//图片
private String pic;
public String getPic() {return pic;}
public void setPic(String pic) {this.pic = pic;}
}