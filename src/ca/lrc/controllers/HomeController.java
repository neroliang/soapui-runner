package ca.lrc.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.lrc.beans.Report;
import ca.lrc.beans.Result;
import ca.lrc.services.SoapUIHandler;

import com.eviware.soapui.impl.wsdl.WsdlProject;

@Controller
public class HomeController {
	// DAO dao = new DAO();
	private SoapUIHandler handler = new SoapUIHandler();

	@Autowired
	ServletContext servletContext;

	@RequestMapping("/")
	public String showHome(Model model) {
		/*
		 * List<Report> reportList = dao.getReports();
		 * model.addAttribute("reportList", reportList);
		 */
		return "home";
	}

	/*
	 * @RequestMapping(value = "view/{id}") public String viewReport(Model
	 * model, @PathVariable int id) { Report report =
	 * dao.getReportById(id).get(0); model.addAttribute("reportList", report);
	 * return "display-report"; }
	 */

	@RequestMapping("new-uptime-report")
	public String runReport(Model model) throws Exception {
		WsdlProject project = new WsdlProject(
				servletContext.getRealPath("/ServiceUptime-soapui-project.xml"));
		Report report = handler.runTests(project);
		System.out.println("Report done running");
		List<Result> resultList = report.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println("\nName: " + resultList.get(i).getName()
					+ "\n Success flag: " + resultList.get(i).getSuccessFlag()
					+ "\n Reason for failing: "
					+ resultList.get(i).getReasonForFailing());
		}
		/*
		 * dao.saveUptimeReport(report); List<Report> reportList =
		 * dao.getReports();
		 */
		model.addAttribute("resultList", resultList);
		return "display-report";
	}
}
