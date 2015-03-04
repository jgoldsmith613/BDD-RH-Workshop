package com.bdd.controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.bdd.model.Room;

@Path("/room")
public class RoomBookingController {

	@GET
	@Produces("application/json")
	public Response test() {
		Room room = new Room();
		room.setName("RH");
		room.setCapacity(10);
		Set<String> tags = new HashSet<String>();
		tags.add("video");
		tags.add("phone");
		room.setTags(tags);
		return Response.status(200).entity(room).build();
	}
}
