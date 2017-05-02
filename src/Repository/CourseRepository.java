package Repository;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import IRepository.ICourseRepository;
import Model.*;

@Repository
public class CourseRepository implements ICourseRepository{
	
	@Autowired
	private SessionFactory sessionFactory;

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
	
	
}
