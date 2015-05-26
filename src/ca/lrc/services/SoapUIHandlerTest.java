package ca.lrc.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import ca.lrc.beans.Report;
import ca.lrc.beans.Result;

public class SoapUIHandlerTest {
	private List<Result> testTestCaseResultList;
	private Report expectedReport;

	@Test
	public void testRunTestSuite() throws Exception {
		expectedReport = new Report();
		testTestCaseResultList = new ArrayList<Result>();
		Collections.addAll(testTestCaseResultList, new Result("CommonLookup",
				true, null), new Result("EasrInternal", true, null), new Result(
				"EasrSubmission", true, null),
				new Result("ManageFormTemplate", true, null), new Result(
						"PaymentInternal", true, null), new Result("Site", true, null),
				new Result("CamsManagement", true, null), new Result("CamsProfile",
						true, null), new Result("EcmManagement", true, null), new Result(
						"ExternalSecurity", true, null));
		expectedReport.setTestCaseResultList(testTestCaseResultList);
		Report actualUptimeReport = SoapUIHandler.runTests();

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
