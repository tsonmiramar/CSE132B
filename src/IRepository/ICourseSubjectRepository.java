package IRepository;

import java.util.List;

import Model.CourseSubject;

public interface ICourseSubjectRepository {
	public List<CourseSubject> getAllSubject();
	
	public List<CourseSubject> getSubjectbyDepartment(int departmentId) throws Exception;
}
