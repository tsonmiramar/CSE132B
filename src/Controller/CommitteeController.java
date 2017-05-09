package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Model.CommitteeType;
import Service.StudentService;

@Controller
@RequestMapping("/committee")
public class CommitteeController {
		
		@Autowired
		private StudentService studentService;
		
		@GetMapping("/entry")
		public String getEntryPage(){
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
