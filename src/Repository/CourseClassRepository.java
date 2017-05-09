package Repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.ICourseClassRepository;
import Model.CourseClass;
import Model.Discussion;
import Model.Enrollment;
import Model.NonDiscussion;
import Model.ReviewSession;
import Model.Section;

@Repository
public class CourseClassRepository extends BaseRepository implements ICourseClassRepository {
	
	@Override
	public void insertCourseClass(CourseClass courseClass) {
		Session session = sessionFactory.getCurrentSession();	
		for ( Section s : courseClass.getSectionList() ){
			s.setSectionClass(courseClass);
			
			for ( ReviewSession r : s.getReviewSessionList()){
				r.setSection(s);
			}
			
			for ( Discussion d : s.getDiscussionList()){
				d.setSection(s);
			}
			
			for ( NonDiscussion n : s.getNondiscussionList()){
				n.setSection(s);
			}
		}
		
		session.save(courseClass);		
	}
	
	@Override
	public void addReviewSession(ReviewSession reviewSession){
		Session session = sessionFactory.getCurrentSession();
		session.save(reviewSession);
	}

	@Override
	public void insertEnrollment(Enrollment enrollment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(enrollment);
	}
	
	@Override
	public List<CourseClass> getAllCourseClassByQuarter(String quarter, int year) {
		Session session = sessionFactory.getCurrentSession();	
		
		List<CourseClass> courseClassList = session.createQuery("select c from CourseClass c "
															  + "join c.quarterList q "
															  + "on q.quarterName.name=:quarter and q.year=:year",CourseClass.class)
											.setParameter("quarter", quarter)
											.setParameter("year", year)
											.getResultList();
		return courseClassList;
	}
}
