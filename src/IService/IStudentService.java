package IService;

import java.util.List;

import Model.CommitteeType;
import Model.GradStudent;
import Model.PhDCandidate;
import Model.Probation;
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
}
