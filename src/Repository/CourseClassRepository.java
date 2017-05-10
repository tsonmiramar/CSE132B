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
import Model.Quarter;
import Model.ReviewSession;
import Model.Section;

@Repository
public class CourseClassRepository extends BaseRepository implements ICourseClassRepository {
	
	@Override
	public void insertCourseClass(CourseClass courseClass) {
		Session session = sessionFactory.getCurrentSession();	
		
		@SuppressWarnings("unchecked")
		List<Integer> existingClass = session.createNativeQuery("select c.id from CLASS c where c.id=:courseId")
				.setParameter("courseId", courseClass.getCourse().getId())
				.getResultList();
		
		if ( !existingClass.isEmpty() ){
			courseClass.setId(existingClass.get(0));
			insertClassQuarterandSection(courseClass);
		}
		else{
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
	}
	
	private void insertClassQuarterandSection(CourseClass courseClass) {
		Session session = sessionFactory.getCurrentSession();
		
		//Insert Class quarter
		for ( Quarter quarter : courseClass.getQuarterList()){
			session.createNativeQuery("insert into CLASS_QUARTER values (:class_id,:quarter_id)")
					.setParameter("class_id", courseClass.getId())
					.setParameter("quarter_id",quarter.getId())
					.executeUpdate();
		}
		
		//Insert Class Section and Meeting
		for ( Section section : courseClass.getSectionList() ){
			session.createNativeQuery("insert into SECTION values (:class_id,:faculty_id,:enroll_limit)")
					.setParameter("class_id", courseClass.getId())
					.setParameter("faculty_id", section.getFaculty().getId())
					.setParameter("enroll_limit", section.getEnrollmentLimit())
					.executeUpdate();
			
			int section_id = (Integer)session.createNativeQuery("select max(s.id) from SECTION s").uniqueResult();
			
			//Insert discussion
			for ( Discussion discussion : section.getDiscussionList() ){
				session.createNativeQuery("insert into MEETING values(:start_time,:end_time,:room,:building,:section_id)")
				.setParameter("start_time", discussion.getStartTime())
				.setParameter("end_time", discussion.getEndTime())
				.setParameter("building", discussion.getBuilding())
				.setParameter("room", discussion.getRoom())
				.setParameter("section_id", section_id)
				.executeUpdate();

				int meetingId = (Integer)session.createNativeQuery("select max(m.id) from MEETING m").uniqueResult();
				
				session.createNativeQuery("insert into WEEKLY_MEETING values (:meeting_id,:weekday)")
						.setParameter("meeting_id", meetingId)
						.setParameter("weekday", discussion.getWeekday())
						.executeUpdate();
				
				int weeklymeetingId = (Integer)session.createNativeQuery("select max(w.id) from WEEKLY_MEETING w").uniqueResult();
				
				session.createNativeQuery("insert into DISCUSSION values (:weeklymeetingId,:required)")
						.setParameter("weeklymeetingId", weeklymeetingId)
						.setParameter("required", discussion.isRequired())
						.executeUpdate();		
			}
			
			//Insert Non-discussion
			for ( NonDiscussion nonDiscussion : section.getNondiscussionList() ){
				session.createNativeQuery("insert into MEETING values(:start_time,:end_time,:room,:building,:section_id)")
				.setParameter("start_time", nonDiscussion.getStartTime())
				.setParameter("end_time", nonDiscussion.getEndTime())
				.setParameter("building", nonDiscussion.getBuilding())
				.setParameter("room", nonDiscussion.getRoom())
				.setParameter("section_id", section_id)
				.executeUpdate();
				
				int meetingId = (Integer)session.createNativeQuery("select max(m.id) from MEETING m").uniqueResult();
				
				session.createNativeQuery("insert into WEEKLY_MEETING values (:meeting_id,:weekday)")
						.setParameter("meeting_id", meetingId)
						.setParameter("weekday", nonDiscussion.getWeekday())
						.executeUpdate();
				
				int weeklymeetingId = (Integer)session.createNativeQuery("select max(w.id) from WEEKLY_MEETING w").uniqueResult();
				
				session.createNativeQuery("insert into NON_DISCUSSION values (:weeklymeetingId,:type)")
						.setParameter("weeklymeetingId", weeklymeetingId)
						.setParameter("type", nonDiscussion.getType())
						.executeUpdate();		
			}
		}
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
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "select c.id, cu.symbol, c.currNum, c.unitFrom, c.unitTo from COURSE c "
				+"join COURSE_SUBJECT cu on cu.subject_id = c.subject_id "
				+"join CLASS cs on c.id = cs.id "
				+"join CLASS_QUARTER cq on c.id = cq.class_id "
				+"join QUARTER q on q.id = cq.quarter_id "
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
