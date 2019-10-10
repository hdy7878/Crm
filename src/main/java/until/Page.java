package until;

import java.util.List;

public class Page<T> {
	private int currentPage;
	private int pageSize;


	public Page(int currentPage, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	private int totalRecord;
	private List<T> list;

	public int getTotalPage() {
		return (totalRecord+pageSize-1)/pageSize;
	}
	//上一页
	public int getPreviousPage() {
		if(currentPage>1) {
			return currentPage-1;
		}
		return 1;
	}
	//下一页
	public int getNextPage() {
		if(currentPage<getTotalPage()) {
			return currentPage+1;
		}
		return getTotalPage();
	}
	//第一页
	public int first() {
		return 1;
	}
	//最后一页
	public int last() {
		return getTotalPage();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
