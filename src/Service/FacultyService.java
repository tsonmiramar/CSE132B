package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IFacultyService;
import Model.Faculty;
import Repository.FacultyRepository;

@Service
public class FacultyService implements IFacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	@Transactional
	public List<Faculty> getAllFaculty() {	
		return this.facultyRepository.getAllFaculty();
	}

	@Override
	@Transactional
	public void insertFaculty(Faculty faculty) {
		facultyRepository.insertFaculty(faculty);
	}
	
	@Override
	@Transactional
	public List<Faculty> getAllFacultyByDepartmentId(int dept_id){
		return facultyRepository.getAllFacultyByDepartmentId(dept_id);
	}

}
