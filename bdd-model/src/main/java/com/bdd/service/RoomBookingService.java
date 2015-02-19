package com.bdd.service;

import java.util.Collection;

import org.joda.time.Interval;

import com.bdd.exception.RoomBookingException;
import com.bdd.model.Reservation;
import com.bdd.model.Room;

public interface RoomBookingService {

	public Reservation bookRoom( String user, Room room, Interval interval ) throws RoomBookingException;

	public void cancelRoom( Reservation reservation ) throws RoomBookingException;

	public void modifyRoom( Reservation reservation, Interval interval ) throws RoomBookingException;

	/**
	 * Check if the room is available for the time range given.
	 * 
	 * @return
	 */
	public boolean checkRoomAvailability( Room room, Interval interval );

	/**
	 * Returns the available times for the given room in the specified interval.
	 * 
	 * @param room
	 * @param interval
	 * @return
	 */
	public Collection<Interval> getRoomAvailability( Room room, Interval interval );

	public Collection<Reservation> getReservations( Room room );

}
