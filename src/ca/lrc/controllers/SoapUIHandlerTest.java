package ca.lrc.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import ca.lrc.beans.Report;
import ca.lrc.beans.TestCaseResult;

public class SoapUIHandlerTest {
	private List<TestCaseResult> testTestCaseResultList;
	private Report expectedReport;

	@Test
	public void testRunTestSuite() throws Exception {
		expectedReport = new Report();
		testTestCaseResultList = new ArrayList<TestCaseResult>();
		Collections.addAll(testTestCaseResultList, new TestCaseResult("CommonLookup",
				true, null), new TestCaseResult("EasrInternal", true, null), new TestCaseResult(
				"EasrSubmission", true, null),
				new TestCaseResult("ManageFormTemplate", true, null), new TestCaseResult(
						"PaymentInternal", true, null), new TestCaseResult("Site", true, null),
				new TestCaseResult("CamsManagement", true, null), new TestCaseResult("CamsProfile",
						true, null), new TestCaseResult("EcmManagement", true, null), new TestCaseResult(
						"ExternalSecurity", true, null));
		expectedReport.setTestCaseResultList(testTestCaseResultList);
		Report actualUptimeReport = SoapUIHandler.runTestSuite();

		boolean flag = true;
		System.out.println("Now comparing actual result against expected result. If no error messages are thrown, all is well.");
		//Only works as intended if the status list in the actualResult object is ordered the same as that in the expectedResult object.
		for (int i = 0; i < actualUptimeReport.getTestCaseResultList().size(); i++) {
			if ((expectedReport.getTestCaseResultList().get(i).getName() != actualUptimeReport
					.getTestCaseResultList().get(i).getName())
					&& (expectedReport.getTestCaseResultList().get(i).getSuccessFlag() != actualUptimeReport
							.getTestCaseResultList().get(i).getSuccessFlag())) {
				flag = false;
				System.out.println("Error! " + actualUptimeReport.getTestCaseResultList().get(i).getName() + " actual result does not match expected result.");
			}
		}
		
		assertTrue("Expected result does not match actual result.", flag == true);
	}
}
