package ca.lrc.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.lrc.beans.Report;
import ca.lrc.beans.Result;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class SoapUIHandler {
	public static Report runTests() throws Exception {
		Report report = new Report();

		TestCaseRunner runner = null;
		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));
		WsdlProject project = new WsdlProject(
				"ServiceUptime-soapui-project.xml");
		List<Result> resultList = new ArrayList<Result>();

		for (int i = 0; i < project.getTestSuiteAt(0).getTestCaseList().size(); i++) {
			Result result = new Result();
			result.setSuccessFlag(true);
			result.setName(project.getTestSuiteAt(0).getTestCaseAt(i).getName());
			runner = project.getTestSuiteAt(0).getTestCaseAt(i)
					.run(new PropertiesMap(), false);
			if (runner.getStatus() == Status.FAILED) {
				result.setSuccessFlag(false);
				result.setReasonForFailing(Arrays.toString(runner.getResults()
						.get(0).getMessages()));
				// System.out.println("Current test failed. Reason: " +
				// result.getReasonForFailing());
			}
			resultList.add(result);
			// System.out.println("Current service: " + result.getName() +
			// "\nUptime status: " + result.getSuccessFlag());
		}

		report.setResultList(resultList);
		return report;
	}
}
