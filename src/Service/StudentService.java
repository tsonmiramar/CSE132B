package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IStudentService;
import Model.Probation;
import Model.ResidentStatus;
import Model.Student;
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
	
	@Override
	@Transactional
	public List<Student> getAllStudent() {
		
		return this.studentRepository.getAllStudent();
	}
	
	@Override
	@Transactional
	public void insertProbation(List<Probation> probation) {
		studentRepository.insertProbation(probation);
	}
	
}
