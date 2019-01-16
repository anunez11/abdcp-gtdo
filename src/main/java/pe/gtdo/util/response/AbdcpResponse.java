package pe.gtdo.util.response;

import java.util.List;

public class AbdcpResponse<T> {
	
	private Integer pages;
	private Integer perPage;
	private Integer total;
	private List<T> data;
	
	public Integer getPages() {
		return pages;
	}
	
	
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public Integer getPerPage() {
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
	
}