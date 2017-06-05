package IRepository;

import java.util.List;

import Model.CourseClass;
import Model.CourseClassConflict;
import Model.Enrollment;
import Model.ReviewSession;
import Model.Section;
import Model.WeeklyMeeting;

public interface ICourseClassRepository {
	public void insertCourseClass(CourseClass courseClass);

	void addReviewSession(ReviewSession reviewSession);

	void insertEnrollment(Enrollment enrollment);

	List<CourseClass> getAllCourseClassByQuarter(int quarter_id);

	List<CourseClass> getAllCourseClassByQuarter(String quarter, int year);

	List<CourseClass> getCurrentCourseClassEnrolledByStudentId(int student_id);

	List<CourseClass> getAllCourseClass();

	List<CourseClassConflict> getClassCannotTakebyStudent(int student_id, String quarter, int year);

	List<Section> getAllCurrentQuarterSection();

	List<WeeklyMeeting> getAllAvailableReviewSessionCurrentQuarter(int section_id, int dayFrom_id, int dayTo_id);

	List<Enrollment> getAllCourseClassEnrolledByStudentandQuarter(int student_id, int quarter_id);

	void updateGradebyStudentandSection(Enrollment enrollment);
}
