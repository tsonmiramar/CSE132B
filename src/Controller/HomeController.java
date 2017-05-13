package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showIndexPage(){
		return "index";
	}
	
	@RequestMapping("/report")
	public String showReportIndexPage(){
		return "report_index";
	}
}
