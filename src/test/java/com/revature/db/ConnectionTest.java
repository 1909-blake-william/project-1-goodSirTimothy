package com.revature.db;

import org.junit.Test;

import revature.util.ConnectionUtil;

public class ConnectionTest {

	// Smoke Test
	@Test
	public void connectionFactory_SmokeTest() {
		assert ConnectionUtil.getConnection() != null;
	}
}
