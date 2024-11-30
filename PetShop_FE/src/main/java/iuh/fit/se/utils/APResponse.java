package iuh.fit.se.utils;

import java.util.Map;

public class APResponse {
	private int status;
	private Map<String, Object> error = null;
	private Object data;
	private String message;
	public APResponse(int status, Map<String, Object> error, Object data, String message) {
		super();
		this.status = status;
		this.error = error;
		this.data = data;
		this.message = message;
	}
	public APResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "APResponse [status=" + status + ", error=" + error + ", data=" + data + ", message=" + message + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, Object> getError() {
		return error;
	}
	public void setError(Map<String, Object> error) {
		this.error = error;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
