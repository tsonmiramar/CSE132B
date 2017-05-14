package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Model.CourseClass;
import Model.Student;
import Service.CourseService;
import Service.StudentService;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/classbystudent")
	public String getClassbyStudentPage(Model model){
		List<Student> studentList = studentService.getAllStudent();
		List<CourseClass> courseClassList = courseService.getCurrentCourseClassEnrolledByStudentId(studentList.get(0).getId()); 
		model.addAttribute("studentList", studentList);
		model.addAttribute("courseClassList", courseClassList);
		return "classbystudent_report";
	}
}
