package project.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

//分类
public class Types  extends BaseBean{
//主键
private Integer id;
public Integer getId() {return id;}
public void setId(Integer id) {this.id = id;}
//分类
private String typesName;
public String getTypesName() {return typesName;}
public void setTypesName(String typesName) {this.typesName = typesName;}
}