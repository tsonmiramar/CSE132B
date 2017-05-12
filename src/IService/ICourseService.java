package IService;

import java.util.List;

import Model.*;

public interface ICourseService {
	public List<CourseSubject> getAllSubject();
	
	public List<Course> getAllCourse();
	
	public void insertCourse(Course course);
	
	public void insertCourseClass(CourseClass courseClass);

	void insertReviewSession(ReviewSession reviewSession);

	void insertCourseEnrollment(Enrollment enrollment);

	List<CourseClass> getAllCourseClassByQuarter(int quarter_id);

	List<Course> getAllCourseByDepartment(int id);

	List<CourseClass> getAllCourseClassByQuarter(String string, int year);
}
