package com.jwt.bean;

public class JwtResponse {
	private String jwttoken;
	private String Message;

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public JwtResponse(String jwttoken, String message) {
		super();
		this.jwttoken = jwttoken;
		Message = message;
	}

}
