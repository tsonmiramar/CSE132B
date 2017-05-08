package IRepository;

import java.util.List;

import Model.CourseClass;
import Model.ReviewSession;

public interface ICourseClassRepository {
	public void insertCourseClass(CourseClass courseClass);

	List<CourseClass> getAllCourseClass();

	void addReviewSession(ReviewSession reviewSession);
}
