package pe.gtdo.util.response;

public class AbdcpResponseMessage {
	String message;
	int code;
	Long id;
	Object data;

	public AbdcpResponseMessage(AbdcpMessage m, Long id) {
		this.message = m.getMessage();
		this.code = m.getCode();
		this.id = id;
	}
	
	public AbdcpResponseMessage(AbdcpMessage m, Long id,Object data) {
		this.message = m.getMessage();
		this.code = m.getCode();
		this.id = id;
		this.data=data;
	}
	
	public AbdcpResponseMessage(String message, int code, Long id) {
		super();
		this.message = message;
		this.code = code;
		this.id = id;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
