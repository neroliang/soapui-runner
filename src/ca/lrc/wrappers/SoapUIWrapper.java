package ca.lrc.wrappers;

import java.io.File;
import java.io.PrintWriter;
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

public class SoapUIWrapper {
	public Report runTests(WsdlProject project) throws Exception {
		Report report = new Report();
		File file;
		PrintWriter pw;

		TestCaseRunner runner = null;
		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));

		List<Result> resultList = new ArrayList<Result>();

		for (int i = 0; i < project.getTestSuiteAt(0).getTestCaseList().size(); i++) {
			Result result = new Result();
			result.setSuccessFlag(true);
			result.setTestCaseName(project.getTestSuiteAt(0).getTestCaseAt(i).getName());
			runner = project.getTestSuiteAt(0).getTestCaseAt(i)
					.run(new PropertiesMap(), false);
			if (runner.getStatus() == Status.FAILED) {
				result.setSuccessFlag(false);
				result.setLogName(result.getTestCaseName() + "-" + runner.getResults().get(0).getTimeStamp() + ".txt");
				file = new File(result.getLogName());
				pw = new PrintWriter(file);
				runner.getResults().get(0).writeTo(pw);
				pw.flush();
				pw.close();
			}
			resultList.add(result);
		}

		report.setResultList(resultList);
		return report;
	}
}
