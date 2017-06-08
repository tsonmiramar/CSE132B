package Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.ICourseClassRepository;
import Model.Course;
import Model.CourseClass;
import Model.CourseClassConflict;
import Model.CourseExtraOption;
import Model.CourseSubject;
import Model.CourseUnitNumber;
import Model.Discussion;
import Model.Enrollment;
import Model.Faculty;
import Model.NonDiscussion;
import Model.Quarter;
import Model.QuarterName;
import Model.ReviewSession;
import Model.Section;
import Model.WeeklyMeeting;

@Repository
public class CourseClassRepository extends BaseRepository implements ICourseClassRepository {
	
	@Override
	public void insertCourseClass(CourseClass courseClass) {
		Session session = sessionFactory.getCurrentSession();	
		
		Object row = session.createNativeQuery("select id from CLASS "
				+ "where course_id=:course_id and quarter_id = :quarter_id")
				.setParameter("course_id", courseClass.getCourse().getId())
				.setParameter("quarter_id",courseClass.getQuarter().getId())
				.getSingleResult();
		
		
		if ( row  == null ){
			//Insert to CLASS Table
			session.createNativeQuery("insert into CLASS values (:course_id,:title,:quarter_id")
					.setParameter("course_id", courseClass.getCourse().getId())
					.setParameter("title", courseClass.getTitle())
					.setParameter("quarter_id", courseClass.getQuarter().getId())
					.executeUpdate();
			courseClass.setId((Integer) session.createNativeQuery("select max(id) from CLASS").getSingleResult());
		}
		else {
			courseClass.setId((Integer)row);
		}
		
		for ( Section s : courseClass.getSectionList() ){
			s.setSectionClass(courseClass);
			
			//Insert to SECTION Table
			session.createNativeQuery("insert into SECTION values (:class_id,:faculty_id,:enroll_limit)")
					.setParameter("class_id", s.getSectionClass().getId())
					.setParameter("faculty_id", s.getFaculty().getId())
					.setParameter("enroll_limit", s.getEnrollmentLimit())
					.executeUpdate();
			s.setId((Integer)session.createNativeQuery("select max(id) from SECTION").getSingleResult());
			
			//Insert to MEETING Table
			for ( Discussion d : s.getDiscussionList()){
				d.setSection(s);
				
				session.createNativeQuery("insert into MEETING values (:start_time,:end_time,:room,:building,:section_id)")
						.setParameter("start_time", d.getStartTime())
						.setParameter("end_time", d.getEndTime())
						.setParameter("room", d.getRoom())
						.setParameter("building", d.getBuilding())
						.setParameter("section_id", d.getSection().getId())
						.executeUpdate();
				d.setId((Integer)session.createNativeQuery("select max(id) from MEETING").getSingleResult());
			}
			
			for ( NonDiscussion n : s.getNondiscussionList()){
				n.setSection(s);
				
				session.createNativeQuery("insert into MEETING values (:start_time,:end_time,:room,:building,:section_id)")
					.setParameter("start_time", n.getStartTime())
					.setParameter("end_time", n.getEndTime())
					.setParameter("room", n.getRoom())
					.setParameter("building", n.getBuilding())
					.setParameter("section_id", n.getSection().getId())
					.executeUpdate();
				
				n.setId((Integer)session.createNativeQuery("select max(id) from MEETING").getSingleResult());
			}
			
			//Insert to WEEKLY_MEETING Table
			StringBuilder discussion_val = new StringBuilder();
			StringBuilder nondiscussion_val = new StringBuilder();
			StringBuilder discussion_wm = new StringBuilder();
			StringBuilder nondiscussion_wm = new StringBuilder();
			int i = 0;
			for ( Discussion d : s.getDiscussionList()){
				discussion_wm.append("("+d.getId()+",'"+d.getWeekday()+"')");
				int required = d.isRequired()? 1:0;
				discussion_val.append("("+d.getId()+","+ required +")");
				if ( i < s.getDiscussionList().size() - 1){
					discussion_wm.append(",");
					discussion_val.append(",");
				}
				i++;
			}
			
			i = 0;
			for ( NonDiscussion n : s.getNondiscussionList()){
				nondiscussion_wm.append("("+n.getId()+",'"+n.getWeekday()+"')");
				nondiscussion_val.append("("+n.getId()+",'"+n.getType()+"')");
				if ( i < s.getNondiscussionList().size() - 1){
					nondiscussion_wm.append(",");
					nondiscussion_val.append(",");
				}
				i++;
			}
		
			if (!s.getDiscussionList().isEmpty() && !s.getNondiscussionList().isEmpty() ){
				session.createNativeQuery("insert into WEEKLY_MEETING values "
						+ discussion_wm.toString()+","+nondiscussion_wm.toString())
						.executeUpdate();
			}
			else if ( !s.getDiscussionList().isEmpty() && s.getNondiscussionList().isEmpty()){
				session.createNativeQuery("insert into WEEKLY_MEETING values "
						+ discussion_wm.toString())
						.executeUpdate();
			}
			else if ( s.getDiscussionList().isEmpty() && !s.getNondiscussionList().isEmpty()){
				session.createNativeQuery("insert into WEEKLY_MEETING values "
						+nondiscussion_wm.toString())
						.executeUpdate();
			}
			
			//Insert to Discussion
			if ( !s.getDiscussionList().isEmpty() ){
				session.createNativeQuery("insert into DISCUSSION values "
						+ discussion_val.toString()).executeUpdate();
			}
			
			if ( !s.getNondiscussionList().isEmpty() ){
				session.createNativeQuery("insert into NON_DISCUSSION values "
						+ nondiscussion_val.toString()).executeUpdate();
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
	public List<Enrollment> getAllCourseClassEnrolledByStudentandQuarter(int student_id, int quarter_id){
		Session session = sessionFactory.getCurrentSession();
		List<Enrollment> enrollmentList = new ArrayList<Enrollment>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery( 
				 "select e.id,e.section_id,cs.symbol, co.currNum, e.grade from ENROLLMENT e "
				+"join SECTION s on e.section_id = s.id "
				+"join CLASS c on s.class_id = c.id "
				+"join COURSE co on c.course_id = co.id "
				+"join COURSE_SUBJECT cs on co.subject_id = cs.subject_id " 
				+"where e.student_id = :student_id and c.quarter_id = :quarter_id") 
				.setParameter("student_id", student_id)
				.setParameter("quarter_id", quarter_id)
				.getResultList();
		
		for ( Object[] obj : rset){
			Enrollment enrollment = new Enrollment();
			enrollment.setId((Integer)obj[0]);
			enrollment.setSection(new Section());
			enrollment.getSection().setId((Integer)obj[1]);
			enrollment.getSection().setSectionClass(new CourseClass());
			enrollment.getSection().getSectionClass().setCourse(new Course());
			enrollment.getSection().getSectionClass().getCourse().setCourseSubject(new CourseSubject());
			enrollment.getSection().getSectionClass().getCourse().setCourseUnitNumber(new CourseUnitNumber());
			enrollment.getSection().getSectionClass().getCourse().getCourseSubject().setSymbol((String) obj[2]);
			enrollment.getSection().getSectionClass().getCourse().getCourseUnitNumber().setCurrNum((String)obj[3]);
			
			enrollment.setGrade((String)obj[4]);
			
			enrollmentList.add(enrollment);
		}
		return enrollmentList;
	}
	@Override
	public List<CourseClass> getAllCourseClassByQuarter(int quarter_id) {
		Session session = sessionFactory.getCurrentSession();	
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "select c.id, cs.symbol, cu.currNum, cu.unitFrom, cu.unitTo,cu.letter_option, cu.SU_option from CLASS c "
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
			courseClass.getCourse().setCourseOption(new CourseExtraOption());
			courseClass.getCourse().getCourseOption().setLetter_option((Boolean)obj[5]);
			courseClass.getCourse().getCourseOption().setSu_option((Boolean)obj[6]);
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
				 "select c.id, cs.symbol, cu.currNum, cu.unitFrom, cu.unitTo, cu.letter_option, cu.SU_option from CLASS c "
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
			courseClass.getCourse().setCourseOption(new CourseExtraOption());
			courseClass.getCourse().getCourseOption().setLetter_option((Boolean)obj[5]);
			courseClass.getCourse().getCourseOption().setSu_option((Boolean)obj[6]);
			courseClass.setSectionList(new ArrayList<Section>());
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
	public List<CourseClass> getCurrentCourseClassEnrolledByStudentId(int student_id) {
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "select cj.symbol, cs.currNum, e.unit, e.section_id, f.name, s.enroll_limit, c.title "
				+"from ENROLLMENT e "
				+"join SECTION s on e.section_id = s.id "
				+"join FACULTY f on s.faculty_id = f.id "
				+"join CLASS c on s.class_id = c.id "
				+"join QUARTER q on c.quarter_id = q.id "
				+"join QUARTER_NAME qn on q.name_id = qn.id "
				+"join COURSE cs on c.course_id = cs.id "
				+"join COURSE_SUBJECT cj on cs.subject_id = cj.subject_id "
				+"where qn.name=:quarter and q.year=:year "
				+"and e.student_id = :student_id ")
				.setParameter("quarter", "SPRING")
				.setParameter("year", 2017)
				.setParameter("student_id", student_id)
				.getResultList();
		
		List<CourseClass> courseClassList = new ArrayList<CourseClass>();
		
		for ( Object[] obj : rset ){
			CourseClass courseClass = new CourseClass();
			courseClass.setCourse(new Course());
			courseClass.getCourse().setCourseSubject(new CourseSubject());
			courseClass.getCourse().getCourseSubject().setSymbol((String)obj[0]);
			
			courseClass.getCourse().setCourseUnitNumber(new CourseUnitNumber());
			courseClass.getCourse().getCourseUnitNumber().setCurrNum((String)obj[1]);
			courseClass.getCourse().getCourseUnitNumber().setUnitTo((Integer)obj[2]);
			
			courseClass.setSectionList(new HashSet<Section>());
			Section section = new Section();
			section.setId((Integer)obj[3]);
			section.setFaculty(new Faculty());
			section.getFaculty().setName((String)obj[4]);
			section.setEnrollmentLimit((Integer)obj[5]);
			courseClass.getSectionList().add(section);
			
			courseClass.setTitle((String)obj[6]);
			courseClassList.add(courseClass);
		}
		
		return courseClassList;
	}

	@Override
	public List<CourseClass> getAllCourseClass() {
		
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "select c.id, cu.symbol, cs.currNum, c.title, qn.name, q.year from CLASS c "
				+"join QUARTER q on c.quarter_id = q.id "
				+"join QUARTER_NAME qn on q.name_id = qn.id "
				+"join COURSE cs on c.course_id = cs.id "
				+"join COURSE_SUBJECT cu on cs.subject_id = cu.subject_id"
		).getResultList();
		
		List<CourseClass> courseClassList = new ArrayList<CourseClass>();
		for ( Object[] obj : rset ){
			CourseClass courseClass = new CourseClass();
			courseClass.setId((Integer)obj[0]);
			courseClass.setCourse(new Course());
			courseClass.getCourse().setCourseSubject(new CourseSubject());
			courseClass.getCourse().getCourseSubject().setSymbol((String)obj[1]);
			courseClass.getCourse().setCourseUnitNumber(new CourseUnitNumber());
			courseClass.getCourse().getCourseUnitNumber().setCurrNum((String)obj[2]);
			courseClass.setTitle((String)obj[3]);
			courseClass.setQuarter(new Quarter());
			courseClass.getQuarter().setQuarterName(new QuarterName());
			courseClass.getQuarter().getQuarterName().setName((String)obj[4]);
			courseClass.getQuarter().setYear((Integer)obj[5]);
			
			courseClassList.add(courseClass);
		}
		return courseClassList;
	}
	
	@Override
	public List<CourseClassConflict> getClassCannotTakebyStudent(int student_id, String quarter, int year){
		Session session = sessionFactory.getCurrentSession();
		List<CourseClassConflict> courseClassConflictList = new ArrayList<CourseClassConflict>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
					"with student_enroll_meeting as "
							+"(select m.*, c.id as class_id, wm.weekday from ENROLLMENT e " 
							+"join SECTION s on e.section_id = s.id "
							+"join CLASS c on s.class_id = c.id "
							+"join MEETING m on e.section_id = m.section_id "
							+"join WEEKLY_MEETING wm on m.id = wm.id "
							+"where e.student_id = :student_id and e.grade is null), "
							+"conflict_class as "
							+"(select c.id as class_conflict_id, sem.class_id as class_enrolled_id from WEEKLY_MEETING wm "
							+"join MEETING m on wm.id = m.id "
							+"join SECTION s on m.section_id = s.id "
							+"join CLASS c on s.class_id = c.id "
							+"join QUARTER q on c.quarter_id = q.id "
							+"join QUARTER_NAME qn on q.name_id = qn.id "
							+"join student_enroll_meeting sem on wm.weekday like sem.weekday " 
							+"and m.start_time < sem.end_time " 
							+"and m.end_time > sem.start_time "
							+"where qn.name = :quarter and q.year = :year " 
							+"and c.id not in "
							+"(select class_id from student_enroll_meeting) " 
							+"group by c.id, sem.class_id) "

							+"select cs_conflict.symbol as conflict_symbol, cu_conflict.currNum as conflict_num, c_conflict.title as conflict_title, " 
							+"cs_enrolled.symbol as enrolled_symbol, cu_enrolled.currNum as enrolled_num, c_enrolled.title as enrolled_title " 
							+"from conflict_class cc "
							+"left join CLASS c_conflict on cc.class_conflict_id = c_conflict.id "
							+"left join COURSE cu_conflict on c_conflict.course_id = cu_conflict.id "
							+"left join COURSE_SUBJECT cs_conflict on cu_conflict.subject_id = cs_conflict.subject_id "
							+"left join CLASS c_enrolled on cc.class_enrolled_id = c_enrolled.id "
							+"left join COURSE cu_enrolled on c_enrolled.course_id = cu_enrolled.id "
							+"left join COURSE_SUBJECT cs_enrolled on cu_enrolled.subject_id = cs_enrolled.subject_id" 
						)
						.setParameter("student_id", student_id)
						.setParameter("quarter", quarter)
						.setParameter("year", year)
						.getResultList();
		
		for (Object[] obj : rset){
			CourseClass classCannotTake = new CourseClass();
			classCannotTake.setCourse(new Course());
			classCannotTake.getCourse().setCourseSubject(new CourseSubject());
			classCannotTake.getCourse().getCourseSubject().setSymbol((String)obj[0]);
			classCannotTake.getCourse().setCourseUnitNumber(new CourseUnitNumber());
			classCannotTake.getCourse().getCourseUnitNumber().setCurrNum((String)obj[1]);
			classCannotTake.setTitle((String)obj[2]);
			
			CourseClass classConflictWith = new CourseClass();
			classConflictWith.setCourse(new Course());
			classConflictWith.getCourse().setCourseSubject(new CourseSubject());
			classConflictWith.getCourse().getCourseSubject().setSymbol((String)obj[3]);
			classConflictWith.getCourse().setCourseUnitNumber(new CourseUnitNumber());
			classConflictWith.getCourse().getCourseUnitNumber().setCurrNum((String)obj[4]);
			classConflictWith.setTitle((String)obj[5]);
			
			CourseClassConflict courseClassConflict = new CourseClassConflict();
			courseClassConflict.setClassCannotTake(classCannotTake);
			courseClassConflict.setClassConflictWith(classConflictWith);
			courseClassConflictList.add(courseClassConflict);
		}
		return courseClassConflictList;
	}
	
	@Override
	public List<Section> getAllCurrentQuarterSection(){
		Session session = sessionFactory.getCurrentSession();
		List<Section> sectionList = new ArrayList<Section>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				"select s.id as section_id, cu.symbol, cs.currNum from SECTION s "
						+"join CLASS c on s.class_id = c.id "
						+"join COURSE cs on c.course_id = cs.id "
						+"join COURSE_SUBJECT cu on cs.subject_id = cu.subject_id "
						+"where exists " 
						+"(select e.section_id from ENROLLMENT e where e.section_id = s.id " 
						+"and e.grade is null )"
				).getResultList();
		
		for ( Object[] obj: rset){
			Section section = new Section();
			section.setId((Integer)obj[0]);
			section.setSectionClass(new CourseClass());
			section.getSectionClass().setCourse(new Course());
			
			section.getSectionClass().getCourse().setCourseSubject(new CourseSubject());
			section.getSectionClass().getCourse().setCourseUnitNumber(new CourseUnitNumber());
			
			section.getSectionClass().getCourse().getCourseSubject().setSymbol((String)obj[1]);
			section.getSectionClass().getCourse().getCourseUnitNumber().setCurrNum((String)obj[2]);
			
			sectionList.add(section);
		}
		return sectionList; 
	}

	@Override
	public List<WeeklyMeeting> getAllAvailableReviewSessionCurrentQuarter(int section_id, int dayFrom_id, int dayTo_id) {
		Session session = sessionFactory.getCurrentSession();
		
		List<WeeklyMeeting> reviewSessionList = new ArrayList<WeeklyMeeting>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				"with student_from_section as " 
				+"(select student_id from ENROLLMENT where grade is null and section_id = :section_id), "
				+"current_enroll_meeting as "
				+"(select count(sfs.student_id) as num_student_enroll, m.id as meeting_id, m.start_time, m.end_time,  cast(wm.weekday as varchar) as weekday from ENROLLMENT e "
				+"join MEETING m on m.section_id = e.section_id "
				+"join WEEKLY_MEETING wm on m.id = wm.id "
				+"join student_from_section sfs on e.student_id = sfs.student_id "
				+"group by m.id, m.start_time, m.end_time, cast(wm.weekday as varchar)) "
				+"select wd.*, rsat.start_time, rsat.end_time "
				+"from WEEKDAY wd cross join REVIEW_SESSION_AVAILABLE_TIME rsat "
				+"left outer join current_enroll_meeting cem on rsat.start_time = cem.start_time and rsat.end_time = cem.end_time and wd.day = cem.weekday "
				+"where cem.num_student_enroll is null and wd.id between :dayFrom and :dayTo "
				)
				.setParameter("section_id",section_id)
				.setParameter("dayFrom", dayFrom_id)
				.setParameter("dayTo", dayTo_id)
				.getResultList();
		
		for ( Object[] obj : rset ){
			WeeklyMeeting reviewSession = new WeeklyMeeting();
			
			reviewSession.setWeekday((String)obj[1]);
			reviewSession.setStartTime((Time)obj[2]);
			reviewSession.setEndTime((Time)obj[3]);
			
			reviewSessionList.add(reviewSession);
		}
		return reviewSessionList;
	}

	@Override
	public void updateGradebyStudentandSection(Enrollment enrollment) {
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery(
				 "update ENROLLMENT "
				+ "set grade=:grade "
				+ "where id=:enrollment_id"
				).setParameter("grade", enrollment.grade)
				.setParameter("enrollment_id", enrollment.getId())
		.executeUpdate();	
	}
}
