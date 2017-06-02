package Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Model.ConcentrationCourseClassDA;
import Model.Course;
import Model.CourseClass;
import Model.CourseClassConflict;
import Model.Degree;
import Model.DegreeRemainingDA;
import Model.Enrollment;
import Model.Faculty;
import Model.GradeCount;
import Model.MSConcentration;
import Model.Quarter;
import Model.QuarterGPA_DAO;
import Model.Section;
import Model.Student;
import Model.Weekday;
import Model.WeeklyMeeting;
import Service.CourseService;
import Service.DegreeService;
import Service.FacultyService;
import Service.GradeReportService;
import Service.MeetingService;
import Service.QuarterService;
import Service.StudentService;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private QuarterService quarterService;
	
	@Autowired
	private GradeReportService gradeReportService;
	
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
	
	@GetMapping("/bachelorremaining")
	public String getBachelorRemainingReport(Model model){
		List<Student> studentList = studentService.getStudentEnrollByQuarter("SPRING", 2017);
		List<Degree> degreeList = degreeService.getAllBSCDegree();
		int student_id = studentList.isEmpty() ? 0 : studentList.get(0).getId();
		int degree_id = degreeList.isEmpty() ? 0 : degreeList.get(0).getId();
		int totalRemainingUnit = 0;
		List<DegreeRemainingDA> degreeRemainingList = degreeService.getDegreeRemainingbyStudentandDegree(student_id,degree_id);
		for ( DegreeRemainingDA degreeRemaining : degreeRemainingList){
			totalRemainingUnit += degreeRemaining.getRemainingUnit();
		}
		model.addAttribute("studentList", studentList);
		model.addAttribute("degreeList", degreeList);
		model.addAttribute("degreeRemainingList",degreeRemainingList);
		model.addAttribute("totalRemainingUnit",totalRemainingUnit);
		return "bachelor_remaining_report";
	}
	
	@GetMapping("/masterremaining")
	public String getMasterRemainingReport(Model model){
		List<Student> studentList = studentService.getMasterStudentEnrollByQuarter("SPRING", 2017);
		List<Degree> degreeList = degreeService.getAllMasterDegree();
		int student_id = studentList.isEmpty() ? 0 : studentList.get(0).getId();
		int degree_id = degreeList.isEmpty() ? 0 : degreeList.get(0).getId();
		List<MSConcentration> concentrationList = degreeService.getAllConcentrationCompletedbyStudentWithMSDegree(student_id, degree_id);
		List<ConcentrationCourseClassDA> concentrationCourseClassList = degreeService.getConcentrationandCourseClassNotyetTakenbyStudentwithDegree(student_id, degree_id); 
		model.addAttribute("studentMSList", studentList);
		model.addAttribute("degreeMSList", degreeList);
		model.addAttribute("concentrationCompletedList",concentrationList);
		model.addAttribute("concentrationCourseClassNotYetTakenList", concentrationCourseClassList);
		return "master_remaining_report";
	}
	
	@GetMapping("/conflictclass")
	public String getConflictClassSchedule(Model model){
		List<Student> studentList = studentService.getStudentEnrollByQuarter("SPRING", 2017);
		int student_id = studentList.isEmpty() ? 0 : studentList.get(0).getId();
		List<CourseClassConflict> courseClassConflictList = courseService.getClassCannotTakebyStudent(student_id,"SPRING",2017);
		model.addAttribute("studentList",studentList);
		model.addAttribute("courseClassConflictList",courseClassConflictList);
		return "class_conflict_report";
	}
	
	@GetMapping("/availablereviewsession")
	public String getAvailableReviewSessionHour(Model model){
		List<Section> sectionList = courseService.getAllCurrentQuarterSection();
		List<Weekday> weekdayList = meetingService.getAllWeekday();
		
		int section_id = sectionList.isEmpty() ? 0 : sectionList.get(0).getId();
		int dayFrom_id = 1; //Monday
		int dayTo_id = 5; //Friday
		List<WeeklyMeeting> reviewSessionList = courseService.getAllAvailableReviewSessionCurrentQuarter(section_id,dayFrom_id, dayTo_id); 
		model.addAttribute("sectionList",sectionList);
		model.addAttribute("weekdayList", weekdayList);
		model.addAttribute("reviewSessionList",reviewSessionList);
		return "review_session_report";
	}
	
	@GetMapping("/decisionsupport")
	public String getDecisionSupportReport(Model model){
		List<Course> courseList = courseService.getAllCourse();
		List<Faculty> facultyList = facultyService.getAllFaculty();
		List<Quarter> quarterList = quarterService.getAllQuarter();
		int faculty_id = facultyList.isEmpty() ? 0 : facultyList.get(0).getId();
		int course_id = courseList.isEmpty() ? 0 : courseList.get(0).getId();
		int quarter_id = 48; //Fall 2014
		List<GradeCount> gradeCountFacultyCourseQuarter = gradeReportService.getGradeCountbyFacultyCourseQuarter(faculty_id, course_id, quarter_id);
		List<GradeCount> gradeCountFacultyCourse = gradeReportService.getGradeCountbyFacultyCourse(faculty_id, course_id);
 		List<GradeCount> gradeCountbyCourse = gradeReportService.getGradeCountbyCourse(course_id);
		List<BigDecimal> gradeGPAFacultyCourse = gradeReportService.getGradeGPAFacultyCourse(faculty_id, course_id);
 		model.addAttribute("courseList", courseList);
		model.addAttribute("facultyList",facultyList);
		model.addAttribute("quarterList",quarterList);
		model.addAttribute("gradeCountFacultyCourseQuarterList",gradeCountFacultyCourseQuarter);
		model.addAttribute("gradeCountFacultyCourseList",gradeCountFacultyCourse);
		model.addAttribute("gradeCountByCourseList",gradeCountbyCourse);
		model.addAttribute("gradeGPAFacultyCourseList",gradeGPAFacultyCourse);
		return "decision_support_report";
	}
}
