package com.bdd.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationDTO {

	private String user;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm")
	private Date start;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm")
	private Date end;

	public String getUser() {
		return user;
	}

	public void setUser( String user ) {
		this.user = user;
	}

	public Date getStart() {
		return start;
	}

	public void setStart( Date start ) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd( Date end ) {
		this.end = end;
	}

}
