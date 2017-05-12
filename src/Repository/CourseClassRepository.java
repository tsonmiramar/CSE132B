package Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.ICourseClassRepository;
import Model.Course;
import Model.CourseClass;
import Model.CourseSubject;
import Model.CourseUnitNumber;
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
	public List<CourseClass> getAllCourseClassByQuarter(int quarter_id) {
		Session session = sessionFactory.getCurrentSession();	
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "select c.id, cs.symbol, cu.currNum, cu.unitFrom, cu.unitTo from CLASS c "
				+"join COURSE cu on c.course_id = cu.id "
				+"join COURSE_SUBJECT cs on cu.subject_id = cs.subject_id "
				+"where c.quarter_id=:quarter_id"
				)
				.setParameter("quarter_id", quarter_id)
				.getResultList();
		
		List<CourseClass> courseClassList = new ArrayList<CourseClass>();
		
		for ( Object[] obj : rset ){
			CourseClass courseClass = new CourseClass();
			courseClass.setId((Integer)obj[0]);
			courseClass.setCourse(new Course());
			courseClass.getCourse().setCourseSubject(new CourseSubject());
			courseClass.getCourse().getCourseSubject().setSymbol((String)obj[1]);
			courseClass.getCourse().setCourseUnitNumber(new CourseUnitNumber());
			courseClass.getCourse().getCourseUnitNumber().setCurrNum((String)obj[2]);
			courseClass.getCourse().getCourseUnitNumber().setUnitFrom((Integer)obj[3]);
			courseClass.getCourse().getCourseUnitNumber().setUnitTo((Integer)obj[4]);
			courseClass.setSectionList(new ArrayList<Section>());
			
			@SuppressWarnings("unchecked")
			List<Integer> rset2 = session.createNativeQuery("select s.id from SECTION s where s.class_id=:classId")
									.setParameter("classId", courseClass.getId())
									.getResultList();
			
			for ( Integer obj2 : rset2){
				Section section = new Section();
				section.setId(obj2);
				courseClass.getSectionList().add(section);
			}
			
			courseClassList.add(courseClass);
		}
		return courseClassList;
	}

	@Override
	public List<CourseClass> getAllCourseClassByQuarter(String quarter, int year) {
		Session session = sessionFactory.getCurrentSession();	
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "select c.id, cs.symbol, cu.currNum, cu.unitFrom, cu.unitTo from CLASS c "
				+"join COURSE cu on c.course_id = cu.id "
				+"join COURSE_SUBJECT cs on cu.subject_id = cs.subject_id "
				+"join QUARTER q on q.id = c.quarter_id "
				+"join QUARTER_NAME qn on qn.id = q.name_id "
				+"where qn.name=:quarter and q.year=:year"
				)
				.setParameter("quarter", quarter)
				.setParameter("year", year)
				.getResultList();
		
		List<CourseClass> courseClassList = new ArrayList<CourseClass>();
		
		for ( Object[] obj : rset ){
			CourseClass courseClass = new CourseClass();
			courseClass.setId((Integer)obj[0]);
			courseClass.setCourse(new Course());
			courseClass.getCourse().setCourseSubject(new CourseSubject());
			courseClass.getCourse().getCourseSubject().setSymbol((String)obj[1]);
			courseClass.getCourse().setCourseUnitNumber(new CourseUnitNumber());
			courseClass.getCourse().getCourseUnitNumber().setCurrNum((String)obj[2]);
			courseClass.getCourse().getCourseUnitNumber().setUnitFrom((Integer)obj[3]);
			courseClass.getCourse().getCourseUnitNumber().setUnitTo((Integer)obj[4]);
			courseClass.setSectionList(new ArrayList<Section>());
			
			@SuppressWarnings("unchecked")
			List<Integer> rset2 = session.createNativeQuery("select s.id from SECTION s where s.class_id=:classId")
									.setParameter("classId", courseClass.getId())
									.getResultList();
			
			for ( Integer obj2 : rset2){
				Section section = new Section();
				section.setId(obj2);
				courseClass.getSectionList().add(section);
			}
			
			courseClassList.add(courseClass);
		}
		return courseClassList;
	}
}
