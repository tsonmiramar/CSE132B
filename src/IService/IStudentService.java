package IService;

import java.util.List;

import Model.ResidentStatus;
import Model.StudentType;

public interface IStudentService {
	public void insertStudent(StudentType studentType);

	List<ResidentStatus> getAllResidentStatus();
}
