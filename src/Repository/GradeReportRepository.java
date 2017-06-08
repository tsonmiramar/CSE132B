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
				"select gradeBase, gradeCount from CPQG "
				+"where course_id = :course_id "
				+"and faculty_id = :faculty_id "
				+"and quarter_id = :quarter_id "
				+"order by gradeBase asc"
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
				 "select gradeBase, gradeCount from CPG "
				+"where course_id = :course_id "
				+"and faculty_id = :faculty_id "
				+"order by gradeBase asc")
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
				  "select course_id, gradeBase, sum(gradeCount) as gradeCount from CPG "
				 +"where course_id = :course_id " 
				 +"group by course_id, gradeBase "
				 +"order by course_id, gradeBase")
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
