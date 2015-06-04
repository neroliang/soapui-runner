package ca.lrc.wrappers;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.eviware.soapui.impl.wsdl.WsdlProject;

import ca.lrc.beans.Report;
import ca.lrc.beans.Result;

public class SoapUIWrapperTest {
	private List<Result> testResultList;
	private Report expectedReport;
	private SoapUIWrapper wrapper;
	private WsdlProject project;

	@Test
	public void testRunTestSuite() throws Exception {
		project = new WsdlProject("ServiceUptime-soapui-project.xml");
		expectedReport = new Report();
		testResultList = new ArrayList<Result>();
		wrapper = new SoapUIWrapper();
		Collections.addAll(testResultList, new Result("CommonLookup", true,
				null), new Result("EasrInternal", true, null), new Result(
				"EasrSubmission", true, null), new Result("ManageFormTemplate",
				true, null), new Result("PaymentInternal", true, null),
				new Result("Site", true, null), new Result("CamsManagement",
						true, null), new Result("CamsProfile", true, null),
				new Result("EcmManagement", true, null), new Result(
						"ExternalSecurity", true, null));
		expectedReport.setResultList(testResultList);
		Report actualReport = wrapper.runTests(project);

		boolean flag = true;
		System.out
				.println("\nNow comparing actual result against expected result. If no error messages are thrown, all is well.");
		// Only works as intended if the status list in the actualResult object
		// is ordered the same as that in the expectedResult object.
		for (int i = 0; i < actualReport.getResultList().size(); i++) {
			if ((expectedReport.getResultList().get(i).getName() != actualReport
					.getResultList().get(i).getName())
					&& (expectedReport.getResultList().get(i).getSuccessFlag() != actualReport
							.getResultList().get(i).getSuccessFlag())) {
				if (flag != false) {
					flag = false;
				}
			}
		}
		
		for (Result result : actualReport.getResultList()) {
			if (result.getLog() != null) {
				System.out
						.println("\nError! "
								+ result.getName()
								+ " test case failed. Error log being printed below.\n");
				System.out.println(result.getLog().getContent());
			}

		}

		assertTrue("Expected report does not match actual report.",
				flag == true);
	}
}
