package com.bdd.steps;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Assert;

import cucumber.api.java.Before;

public class DeployedRoomClearingSteps {

	private static final String CLEAR_URL = "http://127.6.9.1:16050/api/clear";

	@Before
	public void clear() {
		Client client = ClientBuilder.newClient();

		WebTarget create = client.target(CLEAR_URL);
		Response delete = create.request().delete();

		if (delete.getStatus() != 200) {
			Assert.fail();
		}
	}

}
