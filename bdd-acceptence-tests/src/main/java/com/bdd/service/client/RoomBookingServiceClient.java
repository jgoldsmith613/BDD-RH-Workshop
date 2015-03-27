package com.bdd.service.client;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.Interval;
import org.junit.Assert;

import com.bdd.dto.ReservationDTO;
import com.bdd.exception.RoomBookingException;
import com.bdd.model.Reservation;
import com.bdd.model.Room;
import com.bdd.service.RoomBookingService;

public class RoomBookingServiceClient implements RoomBookingService {

	private static final String ROOT_URL = "http://localhost:8080/api/room/%s";

	public Reservation bookRoom( String user, Room room, Interval interval ) throws RoomBookingException {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setUser( user );
		reservationDTO.setStart( interval.getStart().toDate() );
		reservationDTO.setEnd( interval.getEnd().toDate() );

		Client client = ClientBuilder.newClient();
		WebTarget create = client.target( String.format( ROOT_URL + "/reservation", room.getName() ) );
		Entity<ReservationDTO> entity = Entity.json( reservationDTO );
		Response post = create.request().header( "Content-Type", MediaType.APPLICATION_JSON ).post( entity );

		if ( post.getStatus() == 200 ) {
			return new Reservation( room, interval, user );
		} else if ( post.getStatus() == 409 ) {
			throw new RoomBookingException( String.format(
					"Room %s is booked for part or all of the period you attempted to book for.", room.getName() ) );
		}
		System.out.println( post.getStatus() );
		System.out.println( post.getHeaders() );
		System.out.println( post );
		System.out.println( post.readEntity( String.class ) );
		Assert.fail();
		return null;
	}

	public void cancelRoom( Reservation reservation ) throws RoomBookingException {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setUser( reservation.getUser() );
		reservationDTO.setStart( reservation.getInterval().getStart().toDate() );
		reservationDTO.setEnd( reservation.getInterval().getEnd().toDate() );

		Client client = ClientBuilder.newClient();
		WebTarget create = client.target( String.format( ROOT_URL, reservation.getRoom().getName() ) + "/deleteReservation" );
		Entity<ReservationDTO> entity = Entity.json( reservationDTO );
		Response put = create.request().put( entity );

		if ( put.getStatus() >= 400 && put.getStatus() < 500 ) {
			Object entityResponse = put.getEntity();
			if ( entityResponse instanceof RoomBookingException ) {
				throw (RoomBookingException) entityResponse;
			}
			Assert.fail();
		}

	}

	public void modifyRoom( Reservation reservation, Interval interval ) throws RoomBookingException {
		// TODO Auto-generated method stub

	}

	public boolean checkRoomAvailability( Room room, Interval interval ) {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<Interval> getRoomAvailability( Room room, Interval interval ) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<Reservation> getReservations( Room room ) {
		Client client = ClientBuilder.newClient();
		WebTarget create = client.target( String.format( ROOT_URL + "/reservation", room.getName() ) );
		Response get = create.request().get();

		if ( get.getStatus() == 200 ) {
			return get.readEntity( Collection.class );
		}

		Assert.fail();
		return null;
	}
}
