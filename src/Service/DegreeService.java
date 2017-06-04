package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IDegreeService;
import Model.ConcentrationCourseClassDA;
import Model.Degree;
import Model.DegreeRemainingDA;
import Model.DegreeType;
import Model.MSConcentration;
import Model.UnitRequirement;
import Repository.DegreeRepository;

@Service
public class DegreeService implements IDegreeService {
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Override
	@Transactional
	public void insertDegreeRequirement(UnitRequirement unitRequirement) {
		degreeRepository.insertDegreeRequirement(unitRequirement);

	}

	@Override
	@Transactional
	public List<DegreeType> getAllDegreeType() {
		return degreeRepository.getAllDegreeType();
	}
	
	@Override
	@Transactional
	public List<Degree> getAllDegree(){
		return degreeRepository.getAllDegree();
	}
	
	@Override
	@Transactional
	public List<Degree> getAllBSCDegree() {
		return degreeRepository.getAllBSCDegree();
	}
	
	@Override
	@Transactional
	public List<DegreeRemainingDA> getDegreeRemainingbyStudentandDegree(int student_id, int degree_id) {
		return degreeRepository.getDegreeRemainingbyStudentandDegree(student_id, degree_id);
	}
	
	@Override
	@Transactional
	public List<MSConcentration> getAllConcentrationCompletedbyStudentWithMSDegree(int student_id,int degree_id){
		return degreeRepository.getAllConcentrationCompletedbyStudentWithMSDegree(student_id, degree_id);
	}

	@Override
	@Transactional
	public List<Degree> getAllMasterDegree() {
		return degreeRepository.getAllMasterDegree();
	} 
	
	@Override
	@Transactional
	public List<ConcentrationCourseClassDA> getConcentrationandCourseClassNotyetTakenbyStudentwithDegree(int student_id, int degree_id){
		return degreeRepository.getConcentrationandCourseClassNotyetTakenbyStudentwithDegree(student_id, degree_id);
	}
}
