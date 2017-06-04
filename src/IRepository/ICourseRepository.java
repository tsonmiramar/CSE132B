package IRepository;

import java.util.List;

import Model.Course;
import Model.CourseType;

public interface ICourseRepository {
	
	public List<Course> getAllCourse();
	
	public void insertCourse(Course course);

	List<Course> getAllCourseByDepartment(int id);

	List<CourseType> getAllCourseType();
}
