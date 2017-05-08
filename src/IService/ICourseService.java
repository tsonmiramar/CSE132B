package IService;

import java.util.List;

import Model.*;

public interface ICourseService {
	public List<CourseSubject> getAllSubject();
	
	public List<Course> getAllCourse();
	
	public void insertCourse(Course course);
	
	public void insertCourseClass(CourseClass courseClass);

	List<CourseClass> getAllCourseClass();

	void insertReviewSession(ReviewSession reviewSession);
}
