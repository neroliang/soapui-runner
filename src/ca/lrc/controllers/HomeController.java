package ca.lrc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.StandaloneSoapUICore;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;

import ca.lrc.beans.Result;
import ca.lrc.beans.Service;
import ca.lrc.dao.DAO;

@Controller
public class HomeController {
	DAO dao = new DAO();

	@RequestMapping("/")
	public String showHome(Model model) {
		List<Result> resultList = dao.getResults();
		model.addAttribute("resultList", resultList);
		return "home";
	}

	@RequestMapping(value = "view/{id}")
	public String viewSubject(Model model, @PathVariable int id) {
		Result result = dao.getResultById(id).get(0);
		model.addAttribute("result", result);
		return "display-result";
	}

	@RequestMapping("save")
	public String saveTopic(Model model) {
		Result result = null; //Don't leave this in. It's only here to suppress an error message.
		dao.saveResult(result);
		List<Result> resultList = dao.getResults();
		model.addAttribute("resultList", resultList);
		return "home";
	}
	
	public static Result runTestSuite() throws Exception {
		Result result = new Result();
		
		TestRunner testRunner = null;
		SoapUI.setSoapUICore(new StandaloneSoapUICore(true));
		
		WsdlProject project = new WsdlProject("service_checks.xml");
		
		List<Service> statusList = new ArrayList<Service>();
		
		for (int i=0; i<project.getTestSuiteAt(0).getTestCaseList().size(); i++) {
			Service service = new Service();
			service.setName(project.getTestSuiteAt(0).getTestCaseAt(i).getName());
			testRunner = project.getTestSuiteAt(0).getTestCaseAt(i).run(new PropertiesMap(), false);
			if (!testRunner.getStatus().equals("FAILED")) {
				service.setIsUp(true);
			}
			statusList.add(service);
		}
		
		result.setStatusList(statusList);
		
		return result;
	}

}
