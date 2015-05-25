package ca.lrc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.lrc.beans.Report;
import ca.lrc.dao.DAO;

@Controller
public class HomeController {
	// DAO dao = new DAO();

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

	@RequestMapping("new")
	public String newReport(Model model) throws Exception {
		Report report = SoapUIHandler.runTestSuite();
/*		dao.saveUptimeReport(report);
		List<Report> reportList = dao.getReports();*/
		model.addAttribute("report", report);
		return "home";
	}
	
}
