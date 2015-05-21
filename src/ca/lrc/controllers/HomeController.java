package ca.lrc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.lrc.beans.Result;
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
	public String saveResult(Model model) throws Exception {
		Result result = SoapUIHandler.runTestSuite();
		dao.saveResult(result);
		List<Result> resultList = dao.getResults();
		model.addAttribute("resultList", resultList);
		return "home";
	}
	
	

}
