package com.bdd.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.dto.ReservationDTO;
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
	public Response registerRoom( Room room ) {
		System.out.println( room );
		return Response.status( 200 ).build();
	}

	@GET
	@Produces("application/json")
	public Response getRooms() {
		Room room1 = new Room();
		room1.setName( "Moma" );
		room1.setCapacity( 8 );
		Set<String> tags = new HashSet<String>();
		tags.add( "video" );
		tags.add( "phone" );
		room1.setTags( tags );

		Room room2 = new Room();
		room2.setName( "Met" );
		room2.setCapacity( 15 );
		Set<String> tags2 = new HashSet<String>();
		tags2.add( "video" );
		tags2.add( "conference" );
		room2.setTags( tags2 );

		Collection<Room> rooms = new ArrayList<Room>();

		rooms.add( room1 );
		rooms.add( room2 );

		return Response.status( 200 ).entity( rooms ).build();

	}

	@GET
	@Path("/{roomName}")
	@Produces("application/json")
	public Response getRoom( @PathParam("roomName") String roomName ) {
		Room room = new Room();
		room.setName( roomName );
		room.setCapacity( 8 );
		Set<String> tags = new HashSet<String>();
		tags.add( "video" );
		tags.add( "phone" );
		room.setTags( tags );

		return Response.status( 200 ).entity( room ).build();
	}

	@POST
	@Produces("application/json")
	@Path("/{roomName}/reservation")
	public Response bookRoom( ReservationDTO reservation, @PathParam("roomName") String roomName ) {
		return Response.status( 200 ).build();
	}

	@PUT
	@Produces("application/json")
	@Path("/{roomName}/deleteReservation")
	public Response cancelRoom( ReservationDTO reservation, @PathParam("roomName") String roomName ) {
		return Response.status( 200 ).build();
	}

	@GET
	@Produces("application/json")
	@Path("/{roomName}/reservation")
	public Response getReservations( @PathParam("roomName") String roomName ) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setUser( "me" );
		reservationDTO.setStart( new Date() );
		reservationDTO.setEnd( new Date() );
		Collection<ReservationDTO> reservationDTOs = new ArrayList<ReservationDTO>();
		reservationDTOs.add( reservationDTO );
		return Response.status( 200 ).entity( reservationDTOs ).build();
	}
}
