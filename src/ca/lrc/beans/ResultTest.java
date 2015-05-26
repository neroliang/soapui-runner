package ca.lrc.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ResultTest {

	private Result testCaseResult;

	@Before
	public void setUp() throws Exception {
		testCaseResult = new Result();
	}
	
	@Test
	public void testSetAndGetName() {
		String testName = "aName";
		assertNull(testCaseResult.getName());
		testCaseResult.setName(testName);
		assertEquals(testName, testCaseResult.getName());
	}
	
	@Test
	public void testSetAndGetSuccessFlag() {
		boolean testSuccessFlag = true;
		assertFalse(testCaseResult.getSuccessFlag());
		testCaseResult.setSuccessFlag(testSuccessFlag);
		assertEquals(testSuccessFlag, testCaseResult.getSuccessFlag());
	}
	
	@Test
	public void testGetAndSetReasonForFailing() {
		String testReasonForFailing = "aReasonForFailing";
		assertNull(testCaseResult.getReasonForFailing());
		testCaseResult.setReasonForFailing(testReasonForFailing);
		assertEquals(testReasonForFailing, testCaseResult.getReasonForFailing());
	}
}
