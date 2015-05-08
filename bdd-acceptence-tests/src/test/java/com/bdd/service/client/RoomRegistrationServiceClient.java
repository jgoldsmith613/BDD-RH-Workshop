package com.bdd.service.client;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.bdd.model.Room;
import com.bdd.service.RoomRegistrationService;

public class RoomRegistrationServiceClient implements RoomRegistrationService {

	private static final String ROOT_URL = "http://localhost:16050/api/room";

	public boolean registerRoom(Room room) {
		if (room == null) {
			return false;
		}
		String name = room.getName();
		if (name == null) {
			return false;
		}

		Room exists = getRoom(name);

		if (exists != null) {
			return false;
		}

		Client client = ClientBuilder.newClient();
		WebTarget create = client.target(ROOT_URL);
		Entity<Room> entity = Entity.json(room);
		Response post = create.request().post(entity);

		return post.getStatus() == 200;

	}

	public Room getRoom(String roomName) {

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(ROOT_URL + "/" + roomName);
		Response response = target.request().get();
		Room room = null;
		if (response.getStatus() == 200) {
			room = response.readEntity(Room.class);
		}

		response.close();

		return room;
	}

	@SuppressWarnings("unchecked")
	public Collection<Room> getAllRooms() {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(ROOT_URL);
		Response response = target.request().get();
		GenericType<Collection<Room>> generic = new GenericType<Collection<Room>>() {
		};
		Collection<Room> rooms = response.readEntity(generic);
		response.close();

		return rooms;
	}
}
