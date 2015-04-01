package com.bdd.controller;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.dto.ReservationDTO;
import com.bdd.exception.RoomBookingException;
import com.bdd.model.Reservation;
import com.bdd.model.Room;
import com.bdd.service.RoomBookingService;
import com.bdd.service.RoomRegistrationService;

@Path("/room")
public class RoomBookingController {

	@Autowired
	private RoomRegistrationService roomRegistrationService;
	@Autowired
	private RoomBookingService roomBookingService;

	@POST
	@Consumes("application/json")
	public Response registerRoom(Room room) {
		boolean value = roomRegistrationService.registerRoom(room);
		int status = 200;
		if (!value) {
			status = 400;
		}
		return Response.status(status).build();
	}

	@GET
	@Produces("application/json")
	public Response getRooms() {
		Collection<Room> rooms = roomRegistrationService.getAllRooms();
		return Response.status(200).entity(rooms).build();

	}

	@GET
	@Path("/{roomName}")
	@Produces("application/json")
	public Response getRoom(@PathParam("roomName") String roomName) {
		Room room = roomRegistrationService.getRoom(roomName);
		if (room == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(room).build();
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/{roomName}/reservation")
	public Response bookRoom(ReservationDTO reservation, @PathParam("roomName") String roomName) {

		Room room = roomRegistrationService.getRoom(roomName);
		Interval interval = new Interval(reservation.getStart().getTime(), reservation.getEnd().getTime());
		try {
			roomBookingService.bookRoom(reservation.getUser(), room, interval);
		} catch (RoomBookingException e) {
			return Response.status(409).entity(e).build();
		}

		return Response.status(200).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/{roomName}/deleteReservation")
	public Response cancelRoom(ReservationDTO reservation, @PathParam("roomName") String roomName) {
		Room room = roomRegistrationService.getRoom(roomName);
		Interval interval = new Interval(reservation.getStart().getTime(), reservation.getEnd().getTime());
		Reservation actual = new Reservation(room, interval, reservation.getUser());
		try {
			roomBookingService.cancelRoom(actual);
		} catch (RoomBookingException e) {
			return Response.status(409).entity(e).build();
		}

		return Response.status(200).build();
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/{roomName}/reservation")
	public Response getReservations(@PathParam("roomName") String roomName) {
		Collection<Reservation> reservations = roomBookingService.getReservations(roomRegistrationService
				.getRoom(roomName));
		return Response.status(200).entity(reservations).build();
	}
}
