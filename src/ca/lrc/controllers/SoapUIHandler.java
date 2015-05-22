package ca.lrc.controllers;

import java.util.ArrayList;
import java.util.List;

import ca.lrc.beans.Result;
import ca.lrc.beans.Service;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class SoapUIHandler {
	public static Result runTestSuite() throws Exception {
		Result result = new Result();

		TestRunner testRunner = null;
		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

		WsdlProject project = new WsdlProject("ServiceUptime-soapui-project.xml");

		List<Service> statusList = new ArrayList<Service>();

		for (int i = 0; i < project.getTestSuiteAt(0).getTestCaseList().size(); i++) {
			Service service = new Service();
			service.setIsUp(true);
			service.setName(project.getTestSuiteAt(0).getTestCaseAt(i)
					.getName());
			testRunner = project.getTestSuiteAt(0).getTestCaseAt(i)
					.run(new PropertiesMap(), false);
			System.out.println(testRunner.getStatus().toString());
			if (testRunner.getStatus() == Status.FAILED) {
				service.setIsUp(false);
			}
			statusList.add(service);
			System.out.println("Current service: " + service.getName() + "\nUptime status: " + service.getIsUp());
		}

		result.setStatusList(statusList);

		return result;
	}
}
