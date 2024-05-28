package project.util;


import java.io.Serializable;

import org.apache.commons.lang.StringUtils;


/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学习免费 下载
 * 供大家下载学 习参考
 */
public class PageTool implements Serializable {
	private static final long serialVersionUID = 5057116053218513101L;

	/**
	 * 当前显示的页面
	 */
	private int currentPage = 1;

	/**
	 * 每页显示的数量
	 */
	private int pageSize = 0;

	/**
	 * 总数量
	 */
	private int size = 0;

	/**
	 * 总共有多少页
	 */
	private int totalPage = 1;

	/**
	 * 开始页面
	 */
	private int startPage = 1;

	/**
	 * 结束页面
	 */
	private int endPage = 10;

	/**
	 * 上一页
	 */
	private int prePage = 0;

	/**
	 * 下一页
	 */
	private int nextPage = 0;

	/**
	 * 页面URL
	 */
	private String href = "";

	/**
	 * 上一页
	 */
	private String preHref = "";
	/**
	 * 下一页
	 */
	private String nextHref = "";

	/**
	 * 处理后的分布html
	 */
	private String htmlStr = "";

	public PageTool(int currentPage, int pageSize, int size) {
		if (size <= 0) {
			this.currentPage = 1;
			this.startPage = 1;
			this.endPage = 1;
			this.prePage = 1;
			this.nextPage = 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		if (size <= 0) {
			size = 0;
		}

		this.pageSize = pageSize;
		this.size = size;

		/** 得到最多页数 */
		if (size % pageSize == 0) {
			this.totalPage = size / pageSize;
		} else {
			this.totalPage = size / pageSize + 1;
		}
		// 如果为0页,就让总页数等于1
		this.totalPage = this.totalPage <= 1 ? 1 : this.totalPage;
		/** 得到开始和结束页 */
		if (this.totalPage < 10) {// 如果不够现实10页
			this.startPage = 1;
			this.endPage = this.totalPage;
		} else if (currentPage <= 5) {// 如果当前现实的页数小于5
			this.startPage = 1;
			this.endPage = 10;
		} else if (this.totalPage > (currentPage + 4)) {// 如果结束页大于最后页
			this.startPage = currentPage - 5;
			this.endPage = currentPage + 4;
		} else {// 如果当前页数够现实10页,但是现在要现实最后10条
			this.startPage = this.totalPage - 9;
			this.endPage = this.totalPage;
		}
		/***/
		this.currentPage = (currentPage >= this.totalPage ? this.totalPage
				: currentPage);
		this.prePage = (this.currentPage > 1 ? (this.currentPage - 1) : 1);
		this.nextPage = (this.currentPage >= this.totalPage ? this.totalPage
				: (this.currentPage + 1));
	}

	public void initHref(String href) {
		if (href == null) {
			throw new NullPointerException("href is must not null");
		}
		this.href = href;
		this.preHref = UrlUtil.parseUrl(href, "p", this.prePage+ StringUtils.EMPTY, true);
		this.nextHref = UrlUtil.parseUrl(href, "p", this.nextPage+ StringUtils.EMPTY, true);
	}

	public void initHtmlStr() {

	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getEndPage() {
		return this.endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getNextHref() {
		return this.nextHref;
	}

	public void setNextHref(String nextHref) {
		this.nextHref = nextHref;
	}

	public int getNextPage() {
		return this.nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPreHref() {
		return this.preHref;
	}

	public void setPreHref(String preHref) {
		this.preHref = preHref;
	}

	public int getPrePage() {
		return this.prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartPage() {
		return this.startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getHtmlStr() {
		return htmlStr;
	}

	public void setHtmlStr(String htmlStr) {
		this.htmlStr = htmlStr;
	}
}
