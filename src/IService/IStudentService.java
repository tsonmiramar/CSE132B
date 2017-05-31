package IService;

import java.math.BigDecimal;
import java.util.List;

import Model.CommitteeType;
import Model.Enrollment;
import Model.GradStudent;
import Model.PhDCandidate;
import Model.Probation;
import Model.QuarterGPA_DAO;
import Model.ResidentStatus;
import Model.Student;
import Model.StudentType;

public interface IStudentService {
	public void insertStudent(StudentType studentType);

	List<ResidentStatus> getAllResidentStatus();

	List<Student> getAllStudent();

	void insertProbation(List<Probation> probation);

	List<PhDCandidate> getAllPhDCandidate();

	List<GradStudent> getAllGradStudent();

	void insertCommittee(CommitteeType committee_type);

	List<Student> getStudentEnrollByQuarter(String quarter, int year);

	List<Enrollment> getAllStudentFromClass(int class_id);

	List<Enrollment> getAllClassWithQuarterGradeByStudent(int student_id);

	List<QuarterGPA_DAO> getQuarterGPAbyStudent(int student_id);

	List<BigDecimal> getCumulativeGPAListbyStudent(int student_id);

	List<Student> getMasterStudentEnrollByQuarter(String quarter, int year);
}
