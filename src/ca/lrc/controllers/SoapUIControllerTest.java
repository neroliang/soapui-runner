package ca.lrc.controllers;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.lrc.beans.Report;
import ca.lrc.beans.Result;

import com.eviware.soapui.impl.wsdl.WsdlProject;

public class SoapUIControllerTest {
	private List<Result> expectedResultList;
	private Report expectedReport;
	private Report actualReport;
	private SoapUIController wrapper;
	private WsdlProject project;

	@Before
	public void setUp() throws Exception {
		expectedReport = new Report();
		expectedResultList = new ArrayList<Result>();

		Collections.addAll(expectedResultList, new Result("CommonLookup", true,
				null), new Result("EasrInternal", true, null), new Result(
				"EasrSubmission", true, null), new Result("ManageFormTemplate",
				true, null), new Result("PaymentInternal", true, null),
				new Result("Site", true, null), new Result("CamsManagement",
						true, null), new Result("CamsProfile", true, null),
				new Result("EcmManagement", true, null), new Result(
						"ExternalSecurity", true, null));
		expectedReport.setResultList(expectedResultList);

		project = new WsdlProject("ServiceUptime-soapui-project.xml");
		wrapper = new SoapUIController();

		actualReport = wrapper.runTests(project);
	}

	@Test
	public void testExpectedReportMatchesActualReport() {
		boolean flag = true;
		System.out
				.println("\nNow comparing actual result against expected result. If no error messages are thrown, all is well.");

		/*
		 * Only works as intended if the status list in the actualResult object
		 * is ordered the same as that in the expectedResult object.
		 */
		for (int currentResultIndex = 0; currentResultIndex < actualReport
				.getResultList().size(); currentResultIndex++) {

			boolean testCaseNamesMatch = (expectedReport.getResultList()
					.get(currentResultIndex).getName().equals(actualReport
					.getResultList().get(currentResultIndex).getName()));
			boolean successFlagsMatch = (expectedReport.getResultList()
					.get(currentResultIndex).getSuccessFlag() == actualReport
					.getResultList().get(currentResultIndex).getSuccessFlag());

			if (!testCaseNamesMatch || !successFlagsMatch) {
				/*
				 * In case more than one test step fails, a check has been added
				 * to prevent the flag from being set as false more than once
				 */
				if (flag != false) {
					flag = false;
				}

				if (!testCaseNamesMatch) {
					System.out.println("Expected test case name: "
							+ expectedReport.getResultList()
									.get(currentResultIndex).getName());
					System.out.println("Actual test case name: "
							+ actualReport.getResultList()
									.get(currentResultIndex).getName());
				}

				if (!successFlagsMatch) {
					System.out.println("Expected boolean value of "
							+ expectedReport.getResultList()
									.get(currentResultIndex).getSuccessFlag()
							+ " for "
							+ actualReport.getResultList()
									.get(currentResultIndex).getName()
							+ " and got a boolean value of "
							+ actualReport.getResultList()
									.get(currentResultIndex).getSuccessFlag()
							+ " instead.");
				}
			}
		}

		if (!flag) {
			testIfErrorLogsExist();
		}

		assertTrue("Expected report does not match actual report.",
				flag == true);
	}

	public void testIfErrorLogsExist() {
		for (Result result : actualReport.getResultList()) {
			if (result.getLog() != null) {
				System.out
						.println("\nError! "
								+ result.getName()
								+ " test case failed. Error log being printed below.\n");
				System.out.println(result.getLog().getContent());
			}
		}
	}
}
