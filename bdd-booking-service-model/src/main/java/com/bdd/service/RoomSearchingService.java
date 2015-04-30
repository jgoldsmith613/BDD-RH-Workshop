package com.bdd.service;

import java.util.Collection;

import org.joda.time.Interval;

import com.bdd.model.Room;

public interface RoomSearchingService {

	public Collection<Room> searchAllRooms( String... tags );

	public Collection<Room> searchAllRooms( int capacity, String... tags );

	public Collection<Room> searchAllRooms( int capacity );

	public Collection<Room> searchAvailableRooms( Interval interval, String... tags );

	public Collection<Room> searchAvailableRooms( Interval interval, int capacity, String... tags );

	public Collection<Room> searchAvailableRooms( Interval interval, int capacity );

}
