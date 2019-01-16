package pe.gtdo.util.request;

public class AbdcpRequestOrderBy {
	String field;
	Boolean isAsc = true;
	
	public AbdcpRequestOrderBy() {
	}
	

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Boolean getIsAsc() {
		return isAsc;
	}
	public void setIsAsc(Boolean isAsc) {
		this.isAsc = isAsc;
	}

	public AbdcpRequestOrderBy(String field, Boolean isAsc) {
		super();
		this.field = field;
		this.isAsc = isAsc;
	}
	
	
	
}
