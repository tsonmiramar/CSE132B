package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.GradeCount;
import Model.GradeGPA;
import Service.GradeReportService;

@RestController
@RequestMapping("/gradereport")
public class GradeReportController {
	
	@Autowired
	private GradeReportService gradeReportService;
	
	@GetMapping("/gradecount/{course_id}")
	public ResponseEntity<List<GradeCount>> getGradeCountbyCourse(@PathVariable int course_id){
		try {
			List<GradeCount> gradeCountList = gradeReportService.getGradeCountbyCourse(course_id);
			return new ResponseEntity<>(gradeCountList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/gradecount/{faculty_id}/{course_id}")
	public ResponseEntity<List<GradeCount>> getGradeCountbyFacultyCourse(@PathVariable int faculty_id, @PathVariable int course_id){
		try {
			List<GradeCount> gradeCountList = gradeReportService.getGradeCountbyFacultyCourse(faculty_id, course_id);
			return new ResponseEntity<>(gradeCountList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/gradecount/{faculty_id}/{course_id}/{quarter_id}")
	public ResponseEntity<List<GradeCount>> getGradeCountbyFacultyQuarterCourse(@PathVariable int faculty_id, @PathVariable int course_id, @PathVariable int quarter_id){
		try {
			List<GradeCount> gradeCountList = gradeReportService.getGradeCountbyFacultyCourseQuarter(faculty_id, course_id, quarter_id);
			return new ResponseEntity<>(gradeCountList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/gradegpa/{faculty_id}/{course_id}")
	public ResponseEntity<List<GradeGPA>> getGradeGPAFacultyCourse(@PathVariable int faculty_id, @PathVariable int course_id){
		try {
			List<GradeGPA> gradeGPAList = gradeReportService.getGradeGPAFacultyCourse(faculty_id, course_id);
			return new ResponseEntity<>(gradeGPAList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
