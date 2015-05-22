package ca.lrc.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.lrc.beans.Result;
import ca.lrc.beans.Service;

public class SoapUIHandlerTest {
	private List<Service> testStatusServiceList;
	private Result expectedResult;

	@Before
	public void setUp() throws Exception {
		expectedResult = new Result();
		testStatusServiceList = new ArrayList<Service>();
	}

	@Test
	public void testRunTestSuite() throws Exception {
		Collections.addAll(testStatusServiceList, new Service("CommonLookup",
				true), new Service("EasrInternal", true), new Service(
				"EasrSubmission", true),
				new Service("ManageFormTemplate", true), new Service(
						"PaymentInternal", true), new Service("Site", true),
				new Service("CamsManagement", true), new Service("CamsProfile",
						true), new Service("EcmManagement", true), new Service(
						"ExternalSecurity", true));
		
		expectedResult.setStatusList(testStatusServiceList);
		
		Result actualResult = SoapUIHandler.runTestSuite();
		assertEquals(expectedResult.getStatusList().get(0).getIsUp(), actualResult.getStatusList().get(0).getIsUp());
	}

}
