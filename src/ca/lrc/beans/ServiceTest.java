package ca.lrc.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

	private Service service;

	@Before
	public void setUp() throws Exception {
		service = new Service();
	}
	
	@Test
	public void testSetAndGetName() {
		String testName = "aName";
		assertNull(service.getName());
		service.setName(testName);
		assertEquals(testName, service.getName());
	}
	
	@Test
	public void testSetAndGetIsUp() {
		boolean testIsUp = true;
		assertFalse(service.getIsUp());
		service.setIsUp(testIsUp);
		assertEquals(testIsUp, service.getIsUp());
	}
}
