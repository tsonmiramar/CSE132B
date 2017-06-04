package Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import Model.CourseClass;
import Model.Faculty;
import Model.ReviewSession;
import Model.WeeklyMeeting;
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
		List<CourseClass> courseClassList = courseService.getAllCourseClassByQuarter("SPRING",2017);
		Map<Integer,Object> courseClassMap = new HashMap<Integer,Object>();
		for ( CourseClass elem : courseClassList ){
			courseClassMap.put(elem.getId(), elem);
		}
		
		Gson jsonObj = new Gson();
		String courseClassJSON = jsonObj.toJson(courseClassMap);
		model.addAttribute("courseClassList",courseClassList);
		model.addAttribute("courseClassJSON",courseClassJSON);
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
	
	@GetMapping("/meeting/availablereviewsession/{section_id}/{dayFrom_id}/{dayTo_id}")
	@ResponseBody
	public ResponseEntity<List<WeeklyMeeting>> getAvailableReviewSession(@PathVariable int section_id,@PathVariable int dayFrom_id, @PathVariable int dayTo_id){
		try{
			List<WeeklyMeeting> reviewSessionList = courseService.getAllAvailableReviewSessionCurrentQuarter(section_id, dayFrom_id, dayTo_id);
			return new ResponseEntity<>(reviewSessionList,HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
