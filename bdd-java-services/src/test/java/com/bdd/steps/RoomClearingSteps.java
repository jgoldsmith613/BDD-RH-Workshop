package com.bdd.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.service.impl.RoomBookingServiceImpl;
import com.bdd.service.impl.RoomRegistrationServiceImpl;

import cucumber.api.java.Before;

public class RoomClearingSteps {

	@Autowired
	private RoomBookingServiceImpl roomBookingService;

	@Autowired
	private RoomRegistrationServiceImpl roomRegistrationService;

	@Before
	public void clear() {
		roomBookingService.clear();
		roomRegistrationService.clear();
	}

}
