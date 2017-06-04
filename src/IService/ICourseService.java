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

	List<CourseClass> getCurrentCourseClassEnrolledByStudentId(int student_id);

	List<CourseClass> getAllCourseClass();

	List<CourseType> getAllCourseType();

	List<CourseClassConflict> getClassCannotTakebyStudent(int student_id, String quarter, int year);

	List<Section> getAllCurrentQuarterSection();

	List<WeeklyMeeting> getAllAvailableReviewSessionCurrentQuarter(int section_id, int dayFrom_id, int dayTo_id);
}
