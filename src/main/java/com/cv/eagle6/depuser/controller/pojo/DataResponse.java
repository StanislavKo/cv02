package com.cv.eagle6.depuser.controller.pojo;

public class DataResponse extends AbstractResponse {

	private Object data;
	
	public DataResponse() {
	}

	public DataResponse(int code, Object data) {
		super(code);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", getClass().getSimpleName(), super.toString());
	}

}
