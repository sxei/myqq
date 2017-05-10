package server.common;

import java.util.ArrayList;
import java.util.List;
public class PageService {
	Page page;   //定义一个page的封闭类
	List listAll; //把总记录数从页面传过来
	public PageService(List listAll) {
		this.listAll = listAll;
		initPage();
	}
/**
 * 初始化分页
 * 
 */
	private void initPage() {
		page = new Page();
		page.setCurrentPage(0);  //初始化分页的当前第0页
		page.setPageSize(5);    //设置每页
		page.setTotalRecords(listAll.size());  //获取页面传过来的总记录数
		
		/**
		 * 计算总页数，第一种： (总记录数+每页显示记录数-1）/每页显示记录数
		 * 
		 * 第二种
		 * if(总记录数%每页显示的记录数！=0)
		 *   总页数=总记录数/每页显示的记录数+1
		 * else
		 *   总页数=总记录数/每页显示的记录数
		 */
		int totalPage = page.getTotalRecords() / page.getPageSize();
		if (page.getTotalRecords() % page.getPageSize() != 0) {
			totalPage++;
		}        
		page.setTotalPage(totalPage);

	}
	
	/**
	 * 当页面是第一页的时候 ，继续点击上一页出现的情况 
	 * 传一个值 ，如果值 小于等于0，就返回第一页
	 * 当页面是最后一页的时候 ，继续点击上一页出现的情况 
	 * 如果 这个值大于总页数，就返回最后一页
	 * 记录每页从哪一条记录开始 
	 * @param target
	 * @return
	 */
	public List gotoPage(int target) {
		if (target <= 0) {
			target = 0;
		} else if (target >= page.getTotalPage() - 1) {
			target = page.getTotalPage() - 1;
		}
		page.setCurrentPage(target);
		List listPerPage = new ArrayList();
		for (int i = target * page.getPageSize(); (i < (target + 1)
				* page.getPageSize())
				&& (i < page.getTotalRecords()); i++) {
			listPerPage.add(listAll.get(i));
		}
		return listPerPage;
	}
	/**
	 * 跳转到首页
	 * @return
	 */
	public List gotoFirst(){
		return this.gotoPage(0);
	}
	/**
	 * 跳转到下一页
	 * @return
	 */
	public List gotoNext(){
		return this.gotoPage(page.getCurrentPage()+1);
	}
	/**
	 * 跳转到上一页，只要当前页-1
	 * @return
	 */
	public List gotoPre(){
		return this.gotoPage(page.getCurrentPage()-1);
		
	}
	/**
	 * 跳转到最后 一页，获取page的totalPage就是最后一页
	 * @return
	 */
	public List gotoLast(){
		return this.gotoPage(page.getTotalPage()-1);
	}
	public int getTotalPage()
	{
		return page.getTotalPage();
	}
	public int getCurrentPage()
	{
		return page.getCurrentPage();
	}
	public int getTotalRecord()
	{
		return page.getTotalRecords();
	}
	public int getPageSize()
	{
		return page.getPageSize();
	}
}
