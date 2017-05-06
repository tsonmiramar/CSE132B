package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IStudentService;
import Model.ResidentStatus;
import Model.StudentType;
import Repository.ResidentStatusRepository;
import Repository.StudentRepository;

@Service
public class StudentService implements IStudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ResidentStatusRepository residentStatusRespository;
	
	@Override
	@Transactional
	public void insertStudent(StudentType studentType) {
		studentRepository.insertStudent(studentType);
	}
	
	@Override
	@Transactional
	public List<ResidentStatus> getAllResidentStatus(){
		return this.residentStatusRespository.getAllResidentStatus();
	}
	
}
