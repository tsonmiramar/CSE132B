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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import Model.CommitteeType;
import Model.DEPARTMENT;
import Model.Faculty;
import Model.GradStudent;
import Model.PhDCandidate;
import Service.DepartmentService;
import Service.FacultyService;
import Service.StudentService;

@Controller
@RequestMapping("/committee")
public class CommitteeController {
		
		@Autowired
		private StudentService studentService;
		
		@Autowired
		private FacultyService facultyService;
		
		@Autowired
		private DepartmentService departmentService;
		
		@GetMapping("/entry")
		public String getEntryPage(Model model){
			Gson jsonObj = new Gson();
			List<GradStudent> gradList = studentService.getAllGradStudent();
			Map<Integer,GradStudent> gradJSON = new HashMap<Integer,GradStudent>();
			for ( GradStudent student : gradList ){
				gradJSON.put(student.getId(), student);
			}
			List<Faculty> facultyList = facultyService.getAllFacultyByDepartmentId(gradList.get(0).getDepartment().getId());
			List<PhDCandidate> phdList = studentService.getAllPhDCandidate();
			List<DEPARTMENT> departmentList = departmentService.getAllDepartment();
			List<Faculty> facultyList2 = facultyService.getAllFacultyByDepartmentId(departmentList.get(0).getId());
			Map<Integer,PhDCandidate> phdJSON = new HashMap<Integer,PhDCandidate>();
			for ( PhDCandidate student : phdList){
				phdJSON.put(student.getId(), student);
			}
			model.addAttribute("gradList", gradList);
			model.addAttribute("gradJSON",jsonObj.toJson(gradJSON));
			model.addAttribute("phdJSON", jsonObj.toJson(phdJSON));
			model.addAttribute("facultyList", facultyList);
			model.addAttribute("departmentList", departmentList);
			model.addAttribute("facultyList2", facultyList2);
			return "thesiscommittee_entry";
		}
		
		@PostMapping("/add")
		@ResponseBody
		public ResponseEntity<Void> addNewCommittee(@RequestBody CommitteeType committee_type){
			try{
				this.studentService.insertCommittee(committee_type);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch(Exception e){
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
}
