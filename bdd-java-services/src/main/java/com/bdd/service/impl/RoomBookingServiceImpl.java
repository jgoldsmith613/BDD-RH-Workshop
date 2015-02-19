package com.bdd.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.Interval;

import com.bdd.exception.RoomBookingException;
import com.bdd.model.Reservation;
import com.bdd.model.Room;
import com.bdd.service.RoomBookingService;

public class RoomBookingServiceImpl implements RoomBookingService {

	private Map<Room, Collection<Reservation>> reservationMap;

	public RoomBookingServiceImpl() {
		reservationMap = new HashMap<Room, Collection<Reservation>>();
	}

	public Reservation bookRoom( String user, Room room, Interval interval ) throws RoomBookingException {
		Reservation reservation = new Reservation( room, interval, user );
		Collection<Reservation> reservations = null;
		if ( reservationMap.containsKey( room ) ) {
			reservations = reservationMap.get( room );
		} else {
			reservations = new ArrayList<Reservation>();
			reservationMap.put( room, reservations );
		}
		if ( !checkOverlap( reservations, reservation ) ) {
			reservations.add( reservation );
		} else {
			throw new RoomBookingException( String.format(
					"Room %s is booked for part or all of the period you attempted to book for.", room.getName() ) );
		}
		return reservation;
	}

	private boolean checkOverlap( Collection<Reservation> reservations, Reservation reservation ) {
		for ( Reservation confirmed : reservations ) {
			if ( confirmed.getInterval().overlaps( reservation.getInterval() ) ) {
				return true;
			}
		}
		return false;
	}

	public void cancelRoom( Reservation reservation ) throws RoomBookingException {
		boolean removed = false;
		if ( reservationMap.containsKey( reservation.getRoom() ) ) {
			Collection<Reservation> reservations = reservationMap.get( reservation.getRoom() );
			removed = reservations.remove( reservation );
		}
		if ( !removed ) {
			throw new RoomBookingException( String.format( "No reservation exists to cancel for room %s at the specified time.",
					reservation.getRoom().getName() ) );
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

	public void clear() {
		reservationMap = new HashMap<Room, Collection<Reservation>>();
	}

	public Collection<Reservation> getReservations( Room room ) {
		return reservationMap.get( room );
	}

}
