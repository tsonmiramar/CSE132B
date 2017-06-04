package IService;

import java.util.List;

import Model.Faculty;

public interface IFacultyService {
	public List<Faculty> getAllFaculty();

	void insertFaculty(Faculty faculty);

	List<Faculty> getAllFacultyByDepartmentId(int dept_id);
}
