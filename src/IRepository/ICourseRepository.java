package IRepository;

import java.util.List;

import Model.Course;

public interface ICourseRepository {
	
	public List<Course> getAllCourse();
	
	public void insertCourse(Course course);
}
