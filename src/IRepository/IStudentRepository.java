package IRepository;

import java.math.BigDecimal;
import java.util.List;

import Model.Enrollment;
import Model.GradStudent;
import Model.PhDCandidate;
import Model.Probation;
import Model.QuarterGPA_DAO;
import Model.Student;
import Model.StudentType;

public interface IStudentRepository {
	
	public void insertStudent(StudentType studentType);

	List<Student> getAllStudent();

	void insertProbation(List<Probation> probation);

	List<PhDCandidate> getAllPhDCandidate();

	List<GradStudent> getAllGradStudent();

	List<Student> getStudentEnrollByQuarter(String quarter, int year);

	List<Enrollment> getAllStudentFromClass(int class_id);

	List<Enrollment> getAllClassWithQuarterGradeByStudent(int student_id);

	List<QuarterGPA_DAO> getQuarterGPAbyStudent(int student_id);

	List<BigDecimal> getCumulativeGPAByStudent(int student_id);
}
