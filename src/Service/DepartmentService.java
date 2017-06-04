package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IService.IDepartmentService;
import Model.DEPARTMENT;
import Repository.DepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<DEPARTMENT> getAllDepartment() {	
		return departmentRepository.getAllDepartment();
	}

}
