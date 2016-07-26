package data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;
import data.Views;

public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	
	@JsonView(Views.Public.class)
	String code;
	
	@JsonView(Views.Public.class)
	List<Integer> aulas;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Integer> getAulas() {
		return aulas;
	}

	public void setAulas(List<Integer> aulas) {
		this.aulas = aulas;
	}

	
}
