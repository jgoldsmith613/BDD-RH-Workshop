package com.bdd.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.exception.RoomBookingException;
import com.bdd.factory.RoomFactory;
import com.bdd.model.Reservation;
import com.bdd.model.Room;
import com.bdd.service.RoomBookingService;
import com.bdd.service.RoomRegistrationService;
import com.bdd.utils.DateUtils;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RoomSteps {

	@Autowired
	private RoomBookingService roomBookingService;

	@Autowired
	private RoomRegistrationService roomRegistrationService;

	private Collection<String> exceptionMessages;

	@Before
	public void clear() {
		exceptionMessages = new ArrayList<String>();
	}

	@Given("^the following rooms:$")
	public void the_following_rooms(DataTable examplesTable) {
		for (Map<String, String> row : examplesTable.asMaps(String.class, String.class)) {
			Room room = RoomFactory.buildRoom(row);
			roomRegistrationService.registerRoom(room);
		}
	}

	@Given("^the following schedules:$")
	public void the_following_schedules(DataTable examplesTable) {
		for (Map<String, String> row : examplesTable.asMaps(String.class, String.class)) {
			Room room = roomRegistrationService.getRoom(row.get("Room Name"));
			Interval interval = makeInterval(row);
			try {
				roomBookingService.bookRoom(row.get("Room Occupant"), room, interval);
			} catch (RoomBookingException e) {
				throw new RuntimeException("Test schedule has overlap");
			}
		}
	}

	@When("^I try to book the following rooms:$")
	public void i_try_to_book_the_following_rooms(DataTable examplesTable) {
		for (Map<String, String> row : examplesTable.asMaps(String.class, String.class)) {
			Room room = roomRegistrationService.getRoom(row.get("Room Name"));
			Interval interval = makeInterval(row);
			try {
				roomBookingService.bookRoom(row.get("Room Occupant"), room, interval);
			} catch (RoomBookingException e) {
				exceptionMessages.add(e.getMessage());
			}
		}
	}

	@When("^I try to cancel the following reservation:$")
	public void i_try_to_cancel_the_following_reservation(DataTable examplesTable) throws Throwable {
		for (Map<String, String> row : examplesTable.asMaps(String.class, String.class)) {
			Room room = roomRegistrationService.getRoom(row.get("Room Name"));
			Interval interval = makeInterval(row);
			Reservation reservation = new Reservation(room, interval, row.get("Room Occupant"));
			try {
				roomBookingService.cancelRoom(reservation);
			} catch (RoomBookingException e) {
				exceptionMessages.add(e.getMessage());
			}
		}
	}

	@Then("^I expect the following schedule to be confirmed:$")
	public void i_expect_the_following_schedule_to_be_confirmed(DataTable examplesTable) {
		int confirmed = 0;
		for (Map<String, String> row : examplesTable.asMaps(String.class, String.class)) {
			Room room = roomRegistrationService.getRoom(row.get("Room Name"));
			Interval interval = makeInterval(row);
			Collection<Reservation> reservations = roomBookingService.getReservations(room);

			Assert.assertTrue(roomBookingService.getReservations(room).contains(
					new Reservation(room, interval, row.get("Room Occupant"))));
			confirmed++;
		}
		int reservationCount = getTotalReservationCount();
		Assert.assertTrue(confirmed == reservationCount);
	}

	@Then("^I expect the following error messages:$")
	public void i_expect_the_following_error_messages(DataTable examplesTable) {
		Assert.assertTrue(exceptionMessages.size() == examplesTable.asMaps(String.class, String.class).size());
		for (Map<String, String> row : examplesTable.asMaps(String.class, String.class)) {
			String message = row.get("Message");
			Assert.assertTrue(exceptionMessages.remove(message));
		}
	}

	@Then("^I expect no error messages$")
	public void i_expect_no_error_messages() throws Throwable {
		Assert.assertTrue(exceptionMessages.size() == 0);
	}

	private Interval makeInterval(Map<String, String> row) {
		DateTime start = DateUtils.asDateTime(row.get("Start Time"));
		DateTime end = DateUtils.asDateTime(row.get("End Time"));
		Interval interval = new Interval(start, end);
		return interval;
	}

	private int getTotalReservationCount() {
		int count = 0;
		for (Room room : roomRegistrationService.getAllRooms()) {
			Collection<Reservation> reservations = roomBookingService.getReservations(room);
			count += reservations == null ? 0 : reservations.size();
		}

		return count;
	}
}
