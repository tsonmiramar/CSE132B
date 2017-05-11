package Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.ICourseRepository;
import Model.Course;
import Model.CourseSubject;
import Model.CourseUnitNumber;

@Repository
public class CourseRepository extends BaseRepository implements ICourseRepository{
	
	@Override
	public List<Course> getAllCourse() {
		Session session = this.sessionFactory.getCurrentSession();
		
		String sql = "select c.id, cs.symbol, c.currNum from COURSE c "
				+ "		join COURSE_SUBJECT cs "
				+ "		on c.subject_id = cs.subject_id";
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(sql).getResultList();
		
		List<Course> courseList = new ArrayList<Course>();
		
		for ( Object[] obj : rset ){
			
			Course course = new Course();
			CourseSubject csubject = new CourseSubject();
			CourseUnitNumber cnumber = new CourseUnitNumber();
			
			course.setId((Integer)obj[0]); // set 'id'
			csubject.setSymbol((String)obj[1]); // set 'symbol'
			cnumber.setCurrNum((String)obj[2]); // set 'currNum'
			course.setCourseSubject(csubject);
			course.setCourseUnitNumber(cnumber);
			
			courseList.add(course);
		}
		return courseList;
	}

	@Override
	public void insertCourse(Course course) {
		Session session = this.sessionFactory.getCurrentSession();
		
		session.save(course);
	}

	@Override
	public List<Course> getAllCourseByDepartment(int id) {
Session session = this.sessionFactory.getCurrentSession();
		
		String sql = "select c.id, cs.symbol, c.currNum from COURSE c "
				+ "		join COURSE_SUBJECT cs "
				+ "		on c.subject_id = cs.subject_id"
				+ "		where cs.dept_id=:dept_id";
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(sql).setParameter("dept_id", id).getResultList();
		
		List<Course> courseList = new ArrayList<Course>();
		
		for ( Object[] obj : rset ){
			
			Course course = new Course();
			CourseSubject csubject = new CourseSubject();
			CourseUnitNumber cnumber = new CourseUnitNumber();
			
			course.setId((Integer)obj[0]); // set 'id'
			csubject.setSymbol((String)obj[1]); // set 'symbol'
			cnumber.setCurrNum((String)obj[2]); // set 'currNum'
			course.setCourseSubject(csubject);
			course.setCourseUnitNumber(cnumber);
			
			courseList.add(course);
		}
		return courseList;
	}
	
	
}
