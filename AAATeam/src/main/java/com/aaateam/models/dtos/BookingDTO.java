package com.aaateam.models.dtos;

import javax.validation.constraints.NotBlank;

public class BookingDTO {
	@NotBlank
	private String username;

	public BookingDTO(@NotBlank String username) {
		super();
		this.username = username;
	}

	public BookingDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
