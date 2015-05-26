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
	private List<Result> testResultList;
	private Report expectedReport;

	@Test
	public void testRunTestSuite() throws Exception {
		expectedReport = new Report();
		testResultList = new ArrayList<Result>();
		Collections.addAll(testResultList, new Result("CommonLookup",
				true, null), new Result("EasrInternal", true, null), new Result(
				"EasrSubmission", true, null),
				new Result("ManageFormTemplate", true, null), new Result(
						"PaymentInternal", true, null), new Result("Site", true, null),
				new Result("CamsManagement", true, null), new Result("CamsProfile",
						true, null), new Result("EcmManagement", true, null), new Result(
						"ExternalSecurity", true, null));
		expectedReport.setResultList(testResultList);
		Report actualReport = SoapUIHandler.runTests();

		boolean flag = true;
		System.out.println("Now comparing actual result against expected result. If no error messages are thrown, all is well.");
		//Only works as intended if the status list in the actualResult object is ordered the same as that in the expectedResult object.
		for (int i = 0; i < actualReport.getResultList().size(); i++) {
			if ((expectedReport.getResultList().get(i).getName() != actualReport
					.getResultList().get(i).getName())
					&& (expectedReport.getResultList().get(i).getSuccessFlag() != actualReport
							.getResultList().get(i).getSuccessFlag())) {
				flag = false;
				System.out.println("Error! " + actualReport.getResultList().get(i).getName() + " actual result does not match expected result.");
			}
		}
		
		assertTrue("Expected report does not match actual report.", flag == true);
	}
}
