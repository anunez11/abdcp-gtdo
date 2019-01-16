package pe.gtdo.util.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbdcpRequest {
	Integer page;// = 1;
	Integer limit; // = 25;
	HashMap<String, Object> where = new HashMap<String, Object>();
	List<AbdcpRequestOrderBy> orderBy = new ArrayList<AbdcpRequestOrderBy>();

	public AbdcpRequest() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public HashMap<String, Object> getWhere() {
		return where;
	}

	public void setWhere(HashMap<String, Object> where) {
		this.where = where;
	}

	public List<AbdcpRequestOrderBy> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(List<AbdcpRequestOrderBy> orderBy) {
		this.orderBy = orderBy;
	}

	public static class CfeRequestBuilder {
		AbdcpRequest cfeRequest;

		public CfeRequestBuilder() {
			cfeRequest = new AbdcpRequest();
		}

		public CfeRequestBuilder setPage(Integer page) {
			cfeRequest.setPage(page);
			return this;
		}

		public CfeRequestBuilder setWhere(HashMap<String, Object> where) {
			cfeRequest.setWhere(where);
			return this;
		}

		public CfeRequestBuilder addWhere(String key, Object value) {
			cfeRequest.getWhere().put(key, value);
			return this;
		}

		public CfeRequestBuilder setOrderBy(List<AbdcpRequestOrderBy> orderBy) {
			cfeRequest.setOrderBy(orderBy);
			return this;
		}

		public CfeRequestBuilder setLimit(Integer limit) {
			cfeRequest.setLimit(limit);
			return this;
		}

		public AbdcpRequest build() {
			return cfeRequest;
		}

	}
}
