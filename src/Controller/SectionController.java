package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Model.Faculty;
import Model.ReviewSession;
import Service.CourseService;
import Service.FacultyService;

@Controller
@RequestMapping("/section")
public class SectionController {

	public static int meetingCounter = 0;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CourseService courseService;
	
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
	
	@GetMapping("/meeting/reviewsession/entry")
	public String getReviewSessionPage(Model model){
		return "reviewsession_entry";
	}
	
	@PostMapping("/meeting/reviewsession/add")
	@ResponseBody
	public ResponseEntity<Void> addReviewSession(@RequestBody ReviewSession reviewSession){
		try{
			this.courseService.insertReviewSession(reviewSession);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
