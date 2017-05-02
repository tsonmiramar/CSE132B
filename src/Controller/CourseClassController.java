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

import Model.Course;
import Model.CourseClass;
import Model.Quarter;
import Service.CourseService;
import Service.QuarterService;

@Controller
@RequestMapping("/class")
public class CourseClassController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private QuarterService quarterService;
	
	@GetMapping("/entry")
	public String getCourseClassEntryPage(Model model){
		List<Course> courseList = courseService.getAllCourse();
		List<Quarter> quarterList = quarterService.getAllQuarter();
		model.addAttribute("courseList", courseList);
		model.addAttribute("quarterList", quarterList);
		return "courseclass_entry";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Void> addCourseClass(@RequestBody CourseClass courseClass){
		try {
			this.courseService.insertCourseClass(courseClass);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
