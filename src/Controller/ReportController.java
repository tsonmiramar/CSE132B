package Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Model.CourseClass;
import Model.Enrollment;
import Model.QuarterGPA_DAO;
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
	
	@GetMapping("/classroster")
	public String getRosterOfClassPage(Model model){
		List<CourseClass> courseClassList = courseService.getAllCourseClass();
		List<Enrollment> enrollmentList = studentService.getAllStudentFromClass(courseClassList.isEmpty() ? 0 : courseClassList.get(0).getId());
		model.addAttribute("courseClassList",courseClassList);
		model.addAttribute("enrollmentList",enrollmentList);
		return "classroster_report";
	}
	
	@GetMapping("/studentgrade")
	public String getStudentGradeReport(Model model){
		List<Student> studentList = studentService.getAllStudent();
		List<Enrollment> enrollmentList = studentService.getAllClassWithQuarterGradeByStudent( studentList.isEmpty() ? 0 : studentList.get(0).getId());	
		List<QuarterGPA_DAO> quarterGPAList = studentService.getQuarterGPAbyStudent(studentList.isEmpty() ? 0 : studentList.get(0).getId());
		List<BigDecimal> cumulativeGPAList = studentService.getCumulativeGPAListbyStudent(studentList.isEmpty() ? 0 : studentList.get(0).getId());
		model.addAttribute("studentList", studentList);
		model.addAttribute("enrollmentList", enrollmentList);
		model.addAttribute("quarterGPAList",quarterGPAList);
		model.addAttribute("cumulativeGPAList",cumulativeGPAList);
		return "studentgrade_report";
	}
}
