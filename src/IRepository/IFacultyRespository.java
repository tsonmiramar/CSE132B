package IRepository;

import java.util.List;

import Model.Faculty;

public interface IFacultyRespository {
	
	public List<Faculty> getAllFaculty();

	void insertFaculty(Faculty faculty);
}
