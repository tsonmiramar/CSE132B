package IRepository;

import java.util.List;

import Model.Probation;
import Model.Student;
import Model.StudentType;

public interface IStudentRepository {
	
	public void insertStudent(StudentType studentType);

	List<Student> getAllStudent();

	void insertProbation(List<Probation> probation);
}
