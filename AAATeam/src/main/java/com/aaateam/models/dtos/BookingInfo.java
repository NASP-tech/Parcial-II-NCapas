package com.aaateam.models.dtos;

import javax.validation.constraints.NotBlank;

public class BookingInfo {
	@NotBlank
	private String username;
	
	@NotBlank
	private String code;

	public BookingInfo(@NotBlank String username, @NotBlank String code) {
		super();
		this.username = username;
		this.code = code;
	}

	public BookingInfo() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
