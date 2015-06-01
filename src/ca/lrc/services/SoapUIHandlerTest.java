package ca.lrc.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.eviware.soapui.impl.wsdl.WsdlProject;

import ca.lrc.beans.Report;
import ca.lrc.beans.Result;

public class SoapUIHandlerTest {
	private List<Result> testResultList;
	private Report expectedReport;
	private SoapUIHandler handler;
	private WsdlProject project;

	@Test
	public void testRunTestSuite() throws Exception {
		project = new WsdlProject("ServiceUptime-soapui-project.xml");
		expectedReport = new Report();
		testResultList = new ArrayList<Result>();
		handler = new SoapUIHandler();
		Collections.addAll(testResultList, new Result("CommonLookup", true,
				null, null), new Result("EasrInternal", true, null, null), new Result(
				"EasrSubmission", true, null, null), new Result("ManageFormTemplate",
				true, null, null), new Result("PaymentInternal", true, null, null),
				new Result("Site", true, null, null), new Result("CamsManagement",
						true, null, null), new Result("CamsProfile", true, null, null),
				new Result("EcmManagement", true, null, null), new Result(
						"ExternalSecurity", true, null, null));
		expectedReport.setResultList(testResultList);
		Report actualReport = handler.runTests(project);

		boolean flag = true;
		System.out
				.println("Now comparing actual result against expected result. If no error messages are thrown, all is well.");
		// Only works as intended if the status list in the actualResult object
		// is ordered the same as that in the expectedResult object.
		for (int i = 0; i < actualReport.getResultList().size(); i++) {
			if ((expectedReport.getResultList().get(i).getName() != actualReport
					.getResultList().get(i).getName())
					&& (expectedReport.getResultList().get(i).getSuccessFlag() != actualReport
							.getResultList().get(i).getSuccessFlag())) {
				flag = false;
				System.out.println("Error! "
						+ actualReport.getResultList().get(i).getName()
						+ " test case failed because of the following reason: "
						+ actualReport.getResultList().get(i)
								.getReasonForFailing());
			}
		}

		assertTrue("Expected report does not match actual report.",
				flag == true);
	}
}
