package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IStudentService;
import Model.CommitteeType;
import Model.Enrollment;
import Model.GradStudent;
import Model.PhDCandidate;
import Model.Probation;
import Model.ResidentStatus;
import Model.Student;
import Model.StudentType;
import Repository.CommitteeRepository;
import Repository.ResidentStatusRepository;
import Repository.StudentRepository;

@Service
public class StudentService implements IStudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ResidentStatusRepository residentStatusRespository;
	
	@Autowired
	private CommitteeRepository committeeRepository;
	
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
	
	@Override
	@Transactional
	public List<PhDCandidate> getAllPhDCandidate() {
		return studentRepository.getAllPhDCandidate();
	}

	@Override
	@Transactional
	public void insertCommittee(CommitteeType committee_type) {
		if ( committee_type.getGradCommittee() != null )
			committeeRepository.insertCommittee(committee_type.getGradCommittee());
		
		if ( committee_type.getPhDCommittee() != null)
			committeeRepository.insertCommittee(committee_type.getPhDCommittee());
	}
	
	@Override
	@Transactional
	public List<GradStudent> getAllGradStudent() {
		return studentRepository.getAllGradStudent();
	}

	@Override
	@Transactional
	public List<Student> getStudentEnrollByQuarter(String quarter, int year) {
		return studentRepository.getStudentEnrollByQuarter(quarter,year);
	}
	
	@Override
	@Transactional
	public List<Enrollment> getAllStudentFromClass(int class_id) {
		return studentRepository.getAllStudentFromClass(class_id);
	}
	
}
