package ca.lrc.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.lrc.beans.Environment;
import ca.lrc.beans.Report;
import ca.lrc.beans.Result;
import ca.lrc.services.SoapUIHandler;

import com.eviware.soapui.impl.wsdl.WsdlProject;

@Controller
public class HomeController {
	// DAO dao = new DAO();
	final static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	public String showDisplayReport(Model model) throws Exception {
/*		WsdlProject project = new WsdlProject(
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
		model.addAttribute("resultList", resultList);*/
		
		Environment environment = new Environment();
		model.addAttribute("environment", environment);
		return "display-report";
	}
	
	@RequestMapping(value="processEnvironmentSelection", method=RequestMethod.GET)
	public String processEnvironmentSelection(Model model, @ModelAttribute Environment environment) throws Exception {
		String userChoice = environment.getSelection();
		if (userChoice.equals("dev")) {
			System.out.println("SoapUI project for development environment hasn't been acquired yet.");
		}
		
		else if (userChoice.equals("uat")) {
			model.addAttribute("resultList", createUptimeReport("/ServiceUptime-soapui-project.xml"));
		}
		
		else if (userChoice.equals("prod")) {
			System.out.println("SoapUI project for production environment hasn't been acquired yet.");
		}
		
		else {
			System.out.println("How in the hell did you trigger this statement?");
		}
		return "display-report";
	}
	
	public List<Result> createUptimeReport(String url) throws Exception {
		WsdlProject project = new WsdlProject(
				servletContext.getRealPath(url));
		Report report = handler.runTests(project);
		System.out.println("Report done running");
		List<Result> resultList = report.getResultList();
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println("\nName: " + resultList.get(i).getName()
					+ "\n Success flag: " + resultList.get(i).getSuccessFlag()
					+ "\n Reason for failing: "
					+ resultList.get(i).getReasonForFailing());
		}
		return resultList;
	}
	
	
}
