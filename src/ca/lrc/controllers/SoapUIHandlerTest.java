package ca.lrc.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import ca.lrc.beans.Result;
import ca.lrc.beans.Service;

public class SoapUIHandlerTest {
	private List<Service> testStatusServiceList;
	private Result expectedResult;

	@Test
	public void testRunTestSuite() throws Exception {
		expectedResult = new Result();
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

		boolean flag = true;
		System.out.println("Now comparing actual result against expected result.");
		//Only works as intended if the status list in the actualResult object is ordered the same as that in the expectedResult object.
		for (int i = 0; i < actualResult.getStatusList().size(); i++) {
			if ((expectedResult.getStatusList().get(i).getName() != actualResult
					.getStatusList().get(i).getName())
					&& (expectedResult.getStatusList().get(i).getIsUp() != actualResult
							.getStatusList().get(i).getIsUp())) {
				flag = false;
				System.out.println("Error! " + actualResult.getStatusList().get(i).getName() + " actual result does not match expected result.");
			}
		}
		
		assertTrue("Expected result does not match actual result.", flag == true);
	}
}
