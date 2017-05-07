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

import Model.DEPARTMENT;
import Model.Faculty;
import Service.DepartmentService;
import Service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired 
	private FacultyService	facultyService;
	
	@GetMapping("/entry")
	public String getEntryPage(Model model){
		List<DEPARTMENT> departmentList = departmentService.getAllDepartment();
		model.addAttribute("departmentList", departmentList);
		return "faculty_entry";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public ResponseEntity<List<Faculty>> getAllFaculty(){
		try {
			List<Faculty> facultyList = facultyService.getAllFaculty();
			return new ResponseEntity<>(facultyList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Void> insertFaculty(@RequestBody Faculty faculty){
		try {
			facultyService.insertFaculty(faculty);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
