package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.Quarter;
import Model.QuarterName;
import Service.QuarterService;

@RestController
@RequestMapping("/quarter")
public class QuarterRestController {
	
	@Autowired
	private QuarterService quarterService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Quarter>> getAllQuarter(){
		try {
			List<Quarter> quarterList = quarterService.getAllQuarter();
			return new ResponseEntity<>(quarterList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/quarterName/list")
	public ResponseEntity<List<QuarterName>> getAllQuarterName(){
		try {
			List<QuarterName> quarterNameList = quarterService.getAllQuarterName();
			return new ResponseEntity<>(quarterNameList,HttpStatus.OK);
		}
		catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
