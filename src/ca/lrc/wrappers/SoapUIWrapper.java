package ca.lrc.wrappers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import ca.lrc.beans.Log;
import ca.lrc.beans.Report;
import ca.lrc.beans.Result;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class SoapUIWrapper {
	private Report report;
	private Result result;
	private PrintWriter pw;
	private TestCaseRunner runner;
	private List<Result> resultList;
	private StringWriter sw;
	
	public Report runTests(WsdlProject project) throws Exception {
		report = new Report();
		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));
		resultList = new ArrayList<Result>();
		sw = new StringWriter();

		for (int i = 0; i < project.getTestSuiteAt(0).getTestCaseList().size(); i++) {
			result = new Result();
			result.setSuccessFlag(true);
			result.setName(project.getTestSuiteAt(0).getTestCaseAt(i).getName());
			runner = project.getTestSuiteAt(0).getTestCaseAt(i)
					.run(new PropertiesMap(), false);
			if (runner.getStatus() == Status.FAILED) {
				result.setSuccessFlag(false);
				pw = new PrintWriter(sw);
				runner.getResults().get(0).writeTo(pw);
				result.setLog(new Log ( sw.toString() ));
				pw.flush();
				sw.flush();
				pw.close();
				sw.close();
			}
			resultList.add(result);
		}

		report.setResultList(resultList);
		return report;
	}
}
