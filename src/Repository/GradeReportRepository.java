package Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IGradeReportRepository;
import Model.GradeCount;

@Repository
public class GradeReportRepository extends BaseRepository implements IGradeReportRepository{

	@Override
	public List<GradeCount> getGradeCountbyCourseFacultyQuarter(int course_id, int faculty_id, int quarter_id) {
		Session session = sessionFactory.getCurrentSession();
		
		List<GradeCount> gradeCountList = new ArrayList<GradeCount>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "with enrollment_grade as "
				+"(select e.grade, count(e.grade) as numGrade from ENROLLMENT e "
				+"join SECTION s on e.section_id = s.id "
				+"join CLASS c on s.class_id = c.id "
				+"where e.grade is not null "
				+"and c.quarter_id = :quarter_id and s.faculty_id = :faculty_id and c.course_id = :course_id " 
				+"group by e.grade )"
				+"select (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as grade, sum(isnull(eg.numGrade,0)) as gradeCount "
				+"from GRADE_CONVERSION gc left outer join enrollment_grade eg "
				+"on gc.letter_grade = eg.grade "
				+"group by (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) "
				).setParameter("quarter_id", quarter_id)
		 		 .setParameter("faculty_id", faculty_id)
		 		 .setParameter("course_id", course_id)
		 		 .getResultList();
		
		for ( Object[] obj : rset) {
			GradeCount gradeCount = new GradeCount();
			gradeCount.setGrade((String)obj[0]);
			gradeCount.setCount((Integer)obj[1]);
			
			gradeCountList.add(gradeCount);
		}
		return gradeCountList;
	}
	
	@Override
	public List<GradeCount> getGradeCountbyCourseFaculty(int faculty_id, int course_id){
		Session session = sessionFactory.getCurrentSession();
		
		List<GradeCount> gradeCountList = new ArrayList<GradeCount>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "with enrollment_grade as "
				+"(select e.grade, count(e.grade) as numGrade from ENROLLMENT e "
				+"join SECTION s on e.section_id = s.id "
				+"join CLASS c on s.class_id = c.id "
				+"where e.grade is not null "
				+"and s.faculty_id = :faculty_id and c.course_id = :course_id " 
				+"group by e.grade )"
				+"select (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as grade, sum(isnull(eg.numGrade,0)) as gradeCount "
				+"from GRADE_CONVERSION gc left outer join enrollment_grade eg "
				+"on gc.letter_grade = eg.grade "
				+"group by (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) "
				)
		 		 .setParameter("faculty_id", faculty_id)
		 		 .setParameter("course_id", course_id)
		 		 .getResultList();
		
		for ( Object[] obj : rset) {
			GradeCount gradeCount = new GradeCount();
			gradeCount.setGrade((String)obj[0]);
			gradeCount.setCount((Integer)obj[1]);
			
			gradeCountList.add(gradeCount);
		}
		return gradeCountList;
	}

	@Override
	public List<GradeCount> getGradeCountbyCourse(int course_id){
		Session session = sessionFactory.getCurrentSession();
		
		List<GradeCount> gradeCountList = new ArrayList<GradeCount>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "with enrollment_grade as "
				+"(select e.grade, count(e.grade) as numGrade from ENROLLMENT e "
				+"join SECTION s on e.section_id = s.id "
				+"join CLASS c on s.class_id = c.id "
				+"where e.grade is not null "
				+"and c.course_id = :course_id " 
				+"group by e.grade )"
				+"select (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) as grade, sum(isnull(eg.numGrade,0)) as gradeCount "
				+"from GRADE_CONVERSION gc left outer join enrollment_grade eg "
				+"on gc.letter_grade = eg.grade "
				+"group by (case when gc.letter_grade like '%+' or gc.letter_grade like '%-' or gc.letter_grade like 'F' then 'other' else gc.letter_grade end) "
				)
		 		 .setParameter("course_id", course_id)
		 		 .getResultList();
		
		for ( Object[] obj : rset) {
			GradeCount gradeCount = new GradeCount();
			gradeCount.setGrade((String)obj[0]);
			gradeCount.setCount((Integer)obj[1]);
			
			gradeCountList.add(gradeCount);
		}
		return gradeCountList;
	}
	
	@Override
	public List<BigDecimal> getGradeGPAFacultyCourse(int faculty_id, int course_id){
		Session session = sessionFactory.getCurrentSession();
		
		List<BigDecimal> gradeGPAList = new ArrayList<BigDecimal>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
					 "select isnull(sum(gc.number_grade*e.unit)/sum(e.unit),0) as gpa from ENROLLMENT e "
					+"join GRADE_CONVERSION gc on e.grade = gc.letter_grade "
					+"join SECTION s on e.section_id = s.id "
					+"join CLASS c on s.class_id = c.id "
					+"where e.grade is not null "
					+"and s.faculty_id = :faculty_id and c.course_id = :course_id "
				)
				 .setParameter("faculty_id", faculty_id)
		 		 .setParameter("course_id", course_id)
		 		 .getResultList();
		
		for ( Object obj : rset) {
			BigDecimal gpa = (BigDecimal)obj;
			gradeGPAList.add(gpa);
		}
		return gradeGPAList;
	}
}
