package ca.lrc.controllers;

import java.util.ArrayList;
import java.util.List;

import ca.lrc.beans.Report;
import ca.lrc.beans.TestCaseResult;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class SoapUIHandler {
	public static Report runTestSuite() throws Exception {
		Report result = new Report();

		TestRunner testRunner = null;
		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

		WsdlProject project = new WsdlProject("ServiceUptime-soapui-project.xml");

		List<TestCaseResult> statusList = new ArrayList<TestCaseResult>();

		for (int i = 0; i < project.getTestSuiteAt(0).getTestCaseList().size(); i++) {
			TestCaseResult service = new TestCaseResult();
			service.setSuccessFlag(true);
			service.setName(project.getTestSuiteAt(0).getTestCaseAt(i)
					.getName());
			testRunner = project.getTestSuiteAt(0).getTestCaseAt(i)
					.run(new PropertiesMap(), false);
			System.out.println(testRunner.getStatus().toString());
			if (testRunner.getStatus() == Status.FAILED) {
				service.setSuccessFlag(false);
			}
			statusList.add(service);
			System.out.println("Current service: " + service.getName() + "\nUptime status: " + service.getSuccessFlag());
		}

		result.setTestCaseResultList(statusList);

		return result;
	}
}
