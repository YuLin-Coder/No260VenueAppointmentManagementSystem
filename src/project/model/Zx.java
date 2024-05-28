package project.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//通知
public class Zx  extends BaseBean{
//主键
private Integer id;
public Integer getId() {return id;}
public void setId(Integer id) {this.id = id;}
//标题
private String title;
//内容
private String content;
//图片
private String pic;
public String getTitle() {return title;}
public void setTitle(String title) {this.title = title;}
public String getContent() {return content;}
public void setContent(String content) {this.content = content;}
public String getPic() {return pic;}
public void setPic(String pic) {this.pic = pic;}
}