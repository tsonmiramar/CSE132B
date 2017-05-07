package IService;

import java.util.List;

import Model.Probation;
import Model.ResidentStatus;
import Model.Student;
import Model.StudentType;

public interface IStudentService {
	public void insertStudent(StudentType studentType);

	List<ResidentStatus> getAllResidentStatus();

	List<Student> getAllStudent();

	void insertProbation(List<Probation> probation);
}
