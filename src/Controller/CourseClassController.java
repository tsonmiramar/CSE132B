package Controller;

import java.util.List;

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

import Model.Course;
import Model.CourseClass;
import Model.Enrollment;
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
	
	@GetMapping("/{quarter}/{year}/list")
	@ResponseBody
	public ResponseEntity<List<CourseClass>> getAllCourseClassByQuarter(@PathVariable String quarter, @PathVariable int year){
		try {
			List<CourseClass> courseClassList = this.courseService.getAllCourseClassByQuarter(quarter,year);
			return new ResponseEntity<>(courseClassList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
	
	@GetMapping("/enrollment/entry")
	public String getEnrollmentEntry(){
		return "enrollment_entry";
	}
	
	@GetMapping("/pastenrollment/entry")
	public String getPastEnrollmentEntry(){
		return "pastenrollment_entry";
	}
	
	@PostMapping("/enrollment/add")
	@ResponseBody
	public ResponseEntity<Void> addClassEnrollment(@RequestBody Enrollment enrollment){
		try {
			this.courseService.insertCourseEnrollment(enrollment);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
