package ca.lrc.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.lrc.beans.Report;
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
/*		List<Report> reportList = dao.getReports();
		model.addAttribute("reportList", reportList);*/
		return "home";
	}

/*	@RequestMapping(value = "view/{id}")
	public String viewReport(Model model, @PathVariable int id) {
		Report report = dao.getReportById(id).get(0);
		model.addAttribute("reportList", report);
		return "display-report";
	}*/

	@RequestMapping("new-report")
	public String runReport(Model model) throws Exception {
		WsdlProject project = new WsdlProject(
				servletContext.getRealPath("/ServiceUptime-soapui-project.xml"));
		Report report = handler.runTests(project);
/*		dao.saveUptimeReport(report);
		List<Report> reportList = dao.getReports();*/
		model.addAttribute("report", report);
		return "home";
	}
	
}
