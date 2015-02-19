package com.bdd.factory;

import java.util.Map;

import com.bdd.model.Room;

public class RoomFactory {

	public static Room buildRoom( Map<String, String> row ) {
		Room room = new Room();
		if ( row.containsKey( "Name" ) ) {
			room.setName( row.get( "Name" ) );
		}
		if ( row.containsKey( "Capacity" ) ) {
			room.setCapacity( Integer.parseInt( row.get( "Capacity" ) ) );
		}

		return room;
	}
}
