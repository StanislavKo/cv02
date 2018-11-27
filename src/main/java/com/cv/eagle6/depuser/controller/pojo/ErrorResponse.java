package com.cv.eagle6.depuser.controller.pojo;

public class ErrorResponse extends AbstractResponse {

	private String errorMessage;

	public ErrorResponse() {
	}

	public ErrorResponse(int code, String errorMessage) {
		super(code);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return String.format("%s(%s, errorMessage=%s)", getClass().getSimpleName(), super.toString(), errorMessage);
	}

}
