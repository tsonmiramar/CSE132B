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

import Model.QuarterName;
import Model.ResidentStatus;
import Model.StudentType;
import Service.QuarterService;
import Service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private QuarterService quarterService;	
	
	@GetMapping("/entry")
	public String getStudentEntryPage(Model model){
		List<QuarterName> quarterNameList = quarterService.getAllQuarterName();
		List<ResidentStatus> residentStatusList = studentService.getAllResidentStatus();
		model.addAttribute("quarterNameList", quarterNameList);
		model.addAttribute("residentStatusList", residentStatusList);
		return "student_entry";
	}
	
	@GetMapping("/{type}/entry")
	public String getSubStudentEntryPage(@PathVariable String type){
		return type+"_entry";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Void> addStudent(@RequestBody StudentType studentType){
		try{
			this.studentService.insertStudent(studentType);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	
}
