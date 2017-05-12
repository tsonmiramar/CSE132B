package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.ICourseService;
import Model.Course;
import Model.CourseClass;
import Model.CourseSubject;
import Model.Enrollment;
import Model.ReviewSession;
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
}
