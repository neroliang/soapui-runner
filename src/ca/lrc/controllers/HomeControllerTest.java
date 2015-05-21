package ca.lrc.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import ca.lrc.beans.Result;
import ca.lrc.beans.Service;

public class HomeControllerTest {
	private List<Service> testStatusServiceList;

	@Test
	public void testRunTestSuite() throws Exception {
		Result expectedResult = new Result();

		testStatusServiceList = new ArrayList<Service>();
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
		
		assertEquals(expectedResult, actualResult);
	}

}
