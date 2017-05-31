package IRepository;

import java.util.List;

import Model.ConcentrationCourseClassDA;
import Model.Degree;
import Model.DegreeRemainingDA;
import Model.DegreeType;
import Model.MSConcentration;
import Model.UnitRequirement;

public interface IDegreeRepository {
	public void insertDegreeRequirement(UnitRequirement unitRequirement);

	List<DegreeType> getAllDegreeType();

	List<Degree> getAllDegree();

	List<Degree> getAllBSCDegree();

	List<DegreeRemainingDA> getDegreeRemainingbyStudentandDegree(int student_id, int degree_id);

	List<MSConcentration> getAllConcentrationCompletedbyStudentWithMSDegree(int student_id, int degree_id);

	List<Degree> getAllMasterDegree();

	List<ConcentrationCourseClassDA> getConcentrationandCourseClassNotyetTakenbyStudentwithDegree(int student_id, int degree_id);
}
