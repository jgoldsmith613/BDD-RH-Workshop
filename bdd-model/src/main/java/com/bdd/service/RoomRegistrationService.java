package com.bdd.service;

import java.util.Collection;

import com.bdd.model.Room;

public interface RoomRegistrationService {

	public boolean registerRoom( Room room );

	public Room getRoom( String roomName );

	public Collection<Room> getAllRooms();

}
