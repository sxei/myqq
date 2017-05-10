package server.common;

public class Page {
	private int totalRecords;   //总记录数
	private int totalPage;     //总页数
	private int pageSize;     //每页显示几条记录
	public static int currentPage;  //当前页
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		Page.currentPage = currentPage;
	}
	
	
	
}
