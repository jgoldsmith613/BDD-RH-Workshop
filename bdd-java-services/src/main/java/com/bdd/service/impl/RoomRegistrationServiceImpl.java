package com.bdd.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.bdd.model.Room;
import com.bdd.service.RoomRegistrationService;

public class RoomRegistrationServiceImpl implements RoomRegistrationService {

	private Map<String, Room> roomMap;

	public RoomRegistrationServiceImpl() {
		roomMap = new HashMap<String, Room>();
	}

	public boolean registerRoom( Room room ) {
		if ( room == null ) {
			return false;
		}
		String name = room.getName();
		if ( name == null ) {
			return false;
		}
		if ( roomMap.containsKey( name ) ) {
			return false;
		}
		roomMap.put( name, room );
		return true;
	}

	public Room getRoom( String roomName ) {
		return roomMap.get( roomName );
	}

	public void clear() {
		roomMap = new HashMap<String, Room>();
	}

	public Set<String> getAllRoomNames() {
		return roomMap.keySet();
	}

}
