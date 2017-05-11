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

import Model.Course;
import Model.CourseClass;
import Model.Enrollment;
import Model.Quarter;
import Model.Student;
import Service.CourseService;
import Service.QuarterService;
import Service.StudentService;

@Controller
@RequestMapping("/class")
public class CourseClassController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private QuarterService quarterService;
	
	@Autowired
	private StudentService studentService; 
	
	@GetMapping("/entry")
	public String getCourseClassEntryPage(Model model){
		List<Course> courseList = courseService.getAllCourse();	
		model.addAttribute("courseList", courseList);
		return "courseclass_entry";
	}
	
	@GetMapping("/quarter/{quarter_id}/list")
	@ResponseBody
	public ResponseEntity<Map<Integer,Object>> getAllCourseClassByQuarter(@PathVariable int quarter_id){
		try {
			List<CourseClass> courseClassList = this.courseService.getAllCourseClassByQuarter(quarter_id);
			Map<Integer,Object> courseClassJSON = new HashMap<Integer,Object>();
			for ( CourseClass courseClass : courseClassList){
				courseClassJSON.put(courseClass.getId(), courseClass);
			}
			return new ResponseEntity<>(courseClassJSON,HttpStatus.OK);
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
	public String getEnrollmentEntry(Model model){
		List<Student> studentList = studentService.getAllStudent();
		List<CourseClass> courseClassList = courseService.getAllCourseClassByQuarter(57); //Spring 2017
		Map<Integer,Object> courseClassMap = new HashMap<Integer,Object>();
		for ( CourseClass elem : courseClassList ){
			courseClassMap.put(elem.getId(), elem);
		}
		
		Gson jsonObj = new Gson();
		String courseClassJSON = jsonObj.toJson(courseClassMap);
		model.addAttribute("studentList",studentList);
		model.addAttribute("courseClassList",courseClassList);
		model.addAttribute("courseClassJSON", courseClassJSON );
		return "enrollment_entry";
	}
	
	@GetMapping("/pastenrollment/entry")
	public String getPastEnrollmentEntry(Model model){
		List<Student> studentList = studentService.getAllStudent();
		List<Quarter> quarterList = quarterService.getAllQuarter();
		List<CourseClass> courseClassList = courseService.getAllCourseClassByQuarter(quarterList.get(0).getId());
		Map<Integer,Object> courseClassMap = new HashMap<Integer,Object>();
		for ( CourseClass elem : courseClassList ){
			courseClassMap.put(elem.getId(), elem);
		}
		
		Gson jsonObj = new Gson();
		String courseClassJSON = jsonObj.toJson(courseClassMap);
		model.addAttribute("studentList",studentList);
		model.addAttribute("quarterList",quarterList);
		model.addAttribute("courseClassList",courseClassList);
		model.addAttribute("courseClassJSON", courseClassJSON );
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
