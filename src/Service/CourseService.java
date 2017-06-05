package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.ICourseService;
import Model.Course;
import Model.CourseClass;
import Model.CourseClassConflict;
import Model.CourseSubject;
import Model.CourseType;
import Model.Enrollment;
import Model.ReviewSession;
import Model.Section;
import Model.WeeklyMeeting;
import Repository.CourseClassRepository;
import Repository.CourseRepository;
import Repository.CourseSubjectRepository;

@Service
public class CourseService implements ICourseService{

	@Autowired
	private CourseSubjectRepository courseSubjectRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseClassRepository courseClassRepository;
	
	@Override
	@Transactional
	public List<CourseSubject> getAllSubject() {
		return this.courseSubjectRepository.getAllSubject();
	}

	@Override
	@Transactional
	public List<Course> getAllCourse() {
		return this.courseRepository.getAllCourse();
	}
	
	@Override
	@Transactional
	public void insertCourse(Course course) {
		this.courseRepository.insertCourse(course);
		
	}

	@Override
	@Transactional
	public void insertCourseClass(CourseClass courseClass) {
		this.courseClassRepository.insertCourseClass(courseClass);
	}

	@Override
	@Transactional
	public void insertReviewSession(ReviewSession reviewSession){
		this.courseClassRepository.addReviewSession(reviewSession);
	}
	
	@Override
	@Transactional
	public void insertCourseEnrollment(Enrollment enrollment){
		this.courseClassRepository.insertEnrollment(enrollment);
	}
	
	@Override
	@Transactional
	public List<CourseClass> getAllCourseClassByQuarter(int quarter_id) {	
		return this.courseClassRepository.getAllCourseClassByQuarter(quarter_id);
	}

	@Override
	@Transactional
	public List<Course> getAllCourseByDepartment(int id) {
		return this.courseRepository.getAllCourseByDepartment(id);
	}
	
	@Override
	@Transactional	
	public List<CourseClass> getAllCourseClassByQuarter(String quarter, int year) {
		return this.courseClassRepository.getAllCourseClassByQuarter(quarter, year);
	}

	@Override
	@Transactional
	public List<CourseClass> getCurrentCourseClassEnrolledByStudentId(int student_id) {
		return this.courseClassRepository.getCurrentCourseClassEnrolledByStudentId(student_id);
	}

	@Override
	@Transactional
	public List<CourseClass> getAllCourseClass() {
		return this.courseClassRepository.getAllCourseClass();
	}
	
	@Override
	@Transactional
	public List<Section> getAllCurrentQuarterSection(){
		return courseClassRepository.getAllCurrentQuarterSection();
	}
	
	@Override
	@Transactional
	public List<CourseClassConflict> getClassCannotTakebyStudent(int student_id, String quarter, int year){
		return courseClassRepository.getClassCannotTakebyStudent(student_id, quarter,year);
	}
	
	@Override
	@Transactional
	public List<CourseType> getAllCourseType(){
		return this.courseRepository.getAllCourseType();
	}
	
	@Override
	@Transactional
	public List<WeeklyMeeting> getAllAvailableReviewSessionCurrentQuarter(int section_id, int dayFrom_id, int dayTo_id) {
		return courseClassRepository.getAllAvailableReviewSessionCurrentQuarter(section_id, dayFrom_id,dayTo_id);
	}
	
	@Override
	@Transactional
	public List<Enrollment> getAllCourseClassEnrolledByStudentandQuarter(int student_id, int quarter_id){
		return courseClassRepository.getAllCourseClassEnrolledByStudentandQuarter(student_id,quarter_id);
	}
}
