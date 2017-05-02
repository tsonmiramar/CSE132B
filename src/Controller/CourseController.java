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

import Model.*;
import Service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/entry")
	public String getCourseEntryPage(Model model){
		
		List<CourseSubject> subjectList = this.courseService.getAllSubject();
		List<Course> courseList = this.courseService.getAllCourse();
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("courseList", courseList);
		
		Course course = new Course();
		course.setCourseOption(new CourseExtraOption());
		course.setCourseSubject(new CourseSubject());
		course.setCourseUnitNumber(new CourseUnitNumber());
		
		model.addAttribute("course",new Course());
		return "course_entry";
		
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Void> addCourse(@RequestBody Course course){
			try {
				this.courseService.insertCourse(course);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch (Exception e){
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	}
}
