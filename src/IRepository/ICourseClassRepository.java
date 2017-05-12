package IRepository;

import java.util.List;

import Model.CourseClass;
import Model.Enrollment;
import Model.ReviewSession;

public interface ICourseClassRepository {
	public void insertCourseClass(CourseClass courseClass);

	void addReviewSession(ReviewSession reviewSession);

	void insertEnrollment(Enrollment enrollment);

	List<CourseClass> getAllCourseClassByQuarter(int quarter_id);

	List<CourseClass> getAllCourseClassByQuarter(String quarter, int year);
}
