package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.ICollegeService;
import Model.College;
import Repository.CollegeRepository;

@Service
public class CollegeService implements ICollegeService{

	@Autowired
	private CollegeRepository collegeRepository;
	
	@Override
	@Transactional
	public List<College> getAllCollege() {
		return collegeRepository.getAllCollege();
	}
	
}
