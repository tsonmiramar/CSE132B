package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.ICourseService;
import Model.Course;
import Model.CourseClass;
import Model.CourseSubject;
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

}
