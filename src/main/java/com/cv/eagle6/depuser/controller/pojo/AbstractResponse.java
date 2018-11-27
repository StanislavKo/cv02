package com.cv.eagle6.depuser.controller.pojo;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractResponse implements Serializable {

	private int code;

	public AbstractResponse() {
	}

	public AbstractResponse(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return String.format("code=%d", code);
	}

}
