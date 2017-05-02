package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Model.Faculty;
import Service.FacultyService;

@Controller
@RequestMapping("/section")
public class SectionController {

	public static int meetingCounter = 0;
	
	@Autowired
	private FacultyService facultyService;
	
	@GetMapping("/entry")
	public String getSectionEntryPage(Model model){
		List<Faculty> facultyList = facultyService.getAllFaculty();
		model.addAttribute("facultyList", facultyList);
		return "section_entry";
	}
	
	@GetMapping("/meeting/entry")
	public String getMeetingEntryPage(Model model){
		model.addAttribute("meetingCounter", SectionController.meetingCounter++);
		return "meeting_entry";
	}
}
