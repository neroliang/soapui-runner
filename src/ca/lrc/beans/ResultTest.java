package ca.lrc.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ResultTest {

	private Result result;

	@Before
	public void setUp() throws Exception {
		result = new Result();
	}
	
	@Test
	public void testSetAndGetName() {
		String testName = "aName";
		assertNull(result.getName());
		result.setName(testName);
		assertEquals(testName, result.getName());
	}
	
	@Test
	public void testSetAndGetSuccessFlag() {
		boolean testSuccessFlag = true;
		assertFalse(result.getSuccessFlag());
		result.setSuccessFlag(testSuccessFlag);
		assertEquals(testSuccessFlag, result.getSuccessFlag());
	}
	
	@Test
	public void testGetAndSetReasonForFailing() {
		String testReasonForFailing = "aReasonForFailing";
		assertNull(result.getReasonForFailing());
		result.setReasonForFailing(testReasonForFailing);
		assertEquals(testReasonForFailing, result.getReasonForFailing());
	}
}
