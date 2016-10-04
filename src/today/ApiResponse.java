package today;

import java.util.List;

public class ApiResponse {
	
	
	private boolean status;
	private String message;
	private List<Event> list;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Event> getList() {
		return list;
	}
	public void setList(List<Event> list) {
		this.list = list;
	}
	
	

}
