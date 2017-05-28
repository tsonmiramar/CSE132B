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
import Model.CourseType;
import Model.DEPARTMENT;
import Model.DegreeType;
import Model.UnitRequirement;
import Service.CourseService;
import Service.DegreeService;
import Service.DepartmentService;

@Controller
@RequestMapping("/degree")
public class DegreeController {
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CourseService courseService; 
	
	@GetMapping("/entry")
	public String getEntryPage(Model model){
		List<DEPARTMENT> departmentList = departmentService.getAllDepartment();
		List<DegreeType> degreeTypeList = degreeService.getAllDegreeType();
		List<Course> courseList = courseService.getAllCourseByDepartment(departmentList.get(0).getId());
		List<CourseType> courseTypeList = courseService.getAllCourseType();
		model.addAttribute("departmentList", departmentList);
		model.addAttribute("degreeTypeList", degreeTypeList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseTypeList", courseTypeList);
		return "degree_entry";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Void> insertDegreeRequirement(@RequestBody UnitRequirement unitRequirement){
		try {
			this.degreeService.insertDegreeRequirement(unitRequirement);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
}
}
