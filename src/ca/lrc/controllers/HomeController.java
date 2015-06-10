package ca.lrc.controllers;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ca.lrc.beans.Environment;
import ca.lrc.beans.Report;

import com.eviware.soapui.impl.wsdl.WsdlProject;

@Controller
@SessionAttributes("report")
public class HomeController {
	// DAO dao = new DAO();

	private SoapUIController handler = new SoapUIController();

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = { "/", "new-uptime-report" })
	public String showDisplayReport(Model model) {
		Environment environment = new Environment();
		model.addAttribute("environment", environment);
		return "display-report";
	}

	@RequestMapping(value = "processEnvironmentSelection", method = RequestMethod.GET)
	public String processEnvironmentSelection(Model model,
			@ModelAttribute Environment environment) throws Exception {
		String userChoice = environment.getSelection();
		if (userChoice.equals("uat")) {
			model.addAttribute("report",
					createUptimeReport("/ServiceUptime-soapui-project.xml"));
		}

		else if (userChoice.equals("prod")) {
			System.out
					.println("SoapUI project for production environment hasn't been acquired yet.");
		}

		else {
			System.out
					.println("How in the hell did you trigger this statement?");
		}
		return "display-report";
	}

	public Report createUptimeReport(String url) throws Exception {
		WsdlProject project = new WsdlProject(servletContext.getRealPath(url));
		Report report = handler.runTests(project);
		return report;
	}

	@RequestMapping(value = "log/{index}")
	public String viewSubject(Model model, @ModelAttribute Report report,
			@PathVariable int index) {
		String log = report.getResultList().get(index).getLog().getContent();
		System.out.println(log);
		model.addAttribute("log", log);
		return "display-log";
	}
}
