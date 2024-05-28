package project.model;

import java.util.Date;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学 习免费下载
 * 供大家下载 学习参考
 */
public class Book2 extends BaseBean{
	
	
	private Long id;
	private String bookName;
	private Double price;
	private Integer num;
	private Date insertDate;
	private Float allfee;
	private String VALSS;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Float getAllfee() {
		return allfee;
	}
	public void setAllfee(Float allfee) {
		this.allfee = allfee;
	}
	public String getVALSS() {
		return VALSS;
	}
	public void setVALSS(String vALSS) {
		VALSS = vALSS;
	}
	
	
}
