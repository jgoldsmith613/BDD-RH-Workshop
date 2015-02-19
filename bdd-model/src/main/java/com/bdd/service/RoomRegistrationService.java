package com.bdd.service;

import java.util.Set;

import com.bdd.model.Room;

public interface RoomRegistrationService {

	public boolean registerRoom( Room room );

	public Room getRoom( String roomName );

	public Set<String> getAllRoomNames();

}
