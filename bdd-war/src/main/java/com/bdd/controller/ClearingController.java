package com.bdd.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.service.impl.RoomBookingServiceImpl;
import com.bdd.service.impl.RoomRegistrationServiceImpl;

/**
 * Never do this in real production software. This is TEST code. Because we have
 * state that is stored as objects in java we need to clear it inbetween tests.
 * In real software that state would be stored outside the application in some
 * kind of database.
 * 
 * @author justin
 *
 */

@Path("/clear")
public class ClearingController {

	@Autowired
	private RoomRegistrationServiceImpl roomRegistrationService;
	@Autowired
	private RoomBookingServiceImpl roomBookingService;

	@DELETE
	public Response clear() {
		roomBookingService.clear();
		roomRegistrationService.clear();
		return Response.status(200).build();
	}
}
