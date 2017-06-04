package Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IDegreeRepository;
import Model.ConcentrationCourseClassDA;
import Model.Course;
import Model.CourseClass;
import Model.CourseSubject;
import Model.CourseUnitNumber;
import Model.Degree;
import Model.DegreeRemainingDA;
import Model.DegreeType;
import Model.MSConcentration;
import Model.Quarter;
import Model.QuarterName;
import Model.UnitCourseCategoryRequirement;
import Model.UnitRequirement;

@Repository
public class DegreeRepository extends BaseRepository implements IDegreeRepository {
	
	@Override
	public void insertDegreeRequirement(UnitRequirement unitRequirement) {
		for ( MSConcentration tuple : unitRequirement.getDegree().getConcentrationCourseList()){
			tuple.setDegreeMaster(unitRequirement.getDegree());
		}
		
		for ( UnitCourseCategoryRequirement tuple : unitRequirement.getTotalUnit().getUnitCourseCategoryRequirement()){
			tuple.setUnitCategory(unitRequirement.getTotalUnit());
		}
		this.sessionFactory.getCurrentSession().save(unitRequirement);
	}

	@Override
	public List<DegreeType> getAllDegreeType() {
		Session session = sessionFactory.getCurrentSession();
		List<DegreeType> degreeTypeList = session.createQuery("from DegreeType",DegreeType.class).getResultList();
		return degreeTypeList;
	}

	@Override
	public List<Degree> getAllDegree() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Degree> degreeList = new ArrayList<Degree>();
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery("select d.id,d.name from DEGREE d").getResultList();
		
		for ( Object[] obj : rset){
			Degree degree = new Degree();
			degree.setId((Integer)obj[0]);
			degree.setName((String)obj[1]);
			degreeList.add(degree);
		}
		return degreeList;
	}

	@Override
	public List<Degree> getAllBSCDegree() {
		Session session = sessionFactory.getCurrentSession();
		List<Degree> bscDegreeList = session.createQuery("from Degree d where d.degreeType.name = 'Bachelors'",Degree.class).getResultList();
		return bscDegreeList;
	}
	
	@Override
	public List<DegreeRemainingDA> getDegreeRemainingbyStudentandDegree(int student_id, int degree_id){
		Session session = sessionFactory.getCurrentSession();
		
		List<DegreeRemainingDA> degreeRemainingList = new ArrayList<DegreeRemainingDA>();
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "with degree_unit as "
				+"(select ccr.type_id, cy.name, ccr.min_unit from DEGREE d " 
				+" join UNIT_REQUIREMENT ur on ur.degree_id = d.id "
				+" join UNIT_CATEGORY uc on ur.totalunit_id = uc.id "	
				+" join UNIT_COURSE_CATEGORY ucc on uc.id = ucc.unit_category_id "
				+" join COURSE_CATEGORY_REQUIREMENT ccr on ucc.course_category_id = ccr.id "
				+" join COURSE_TYPE cy on ccr.type_id = cy.id "
				+" where d.id = :degree_id), "
				+"student_unit as "
				+"(select ct.id, ct.name, sum(e.unit) as unit_per_category from ENROLLMENT e " 
				+" join SECTION st on e.section_id = st.id "
				+" join CLASS c on st.class_id = c.id "
				+" join COURSE cr on c.course_id = cr.id "
				+" join QUARTER q on c.quarter_id = q.id "
				+" join QUARTER_NAME qn on q.name_id = qn.id "
				+" join COURSE_TYPE_CATEGORY ctc on ctc.course_id = cr.id "
				+" join COURSE_TYPE ct on ctc.type_id = ct.id "
				+" where e.student_id = :student_id "
				+" group by ct.id, ct.name) "

				+"select degree_unit.type_id, degree_unit.name, "
				+"case when degree_unit.min_unit-ISNULL(student_unit.unit_per_category,0) < 0 then 0 " 
				+"else degree_unit.min_unit-ISNULL(student_unit.unit_per_category,0) end as remaining_unit "
				+"from " 
				+"degree_unit left outer join student_unit "
				+"on degree_unit.type_id = student_unit.id"
			).setParameter("student_id", student_id)
			 .setParameter("degree_id", degree_id)
			 .getResultList();
		
		for ( Object[] obj : rset ){
			DegreeRemainingDA degreeRemainingDA = new DegreeRemainingDA();
			
			degreeRemainingDA.setName((String)obj[1]);
			degreeRemainingDA.setRemainingUnit((Integer)obj[2]);
			
			degreeRemainingList.add(degreeRemainingDA);
		}
		return degreeRemainingList;
	}

	@Override
	public List<MSConcentration> getAllConcentrationCompletedbyStudentWithMSDegree(int student_id,int degree_id){
		Session session = sessionFactory.getCurrentSession();
		List<MSConcentration> concentrationList = new ArrayList<MSConcentration>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				  "with degree_unit as "
				 +"(select mc.id as concentration_id, mc.name, c.id as course_id, ctr.min_gpa, ctr.min_unit from MS_CONCENTRATION mc " 
				 +"join MS_CONCENTRATION_COURSE mcc on mc.id = mcc.concentration_id "
				 +"join COURSE c on mcc.course_id = c.id "
				 +"join MS_CONCENTRATION_REQUIREMENT mcr on mc.id = mcr.concentration_id "
				 +"join COURSE_CATEGORY_REQUIREMENT ctr on mcr.course_category_id = ctr.id "
				 +"where mc.degree_id = :degree_id ), "
				 +"student_unit as "
				 +"(select  mcc.concentration_id ,sum(gc.number_grade*e.unit)/sum(e.unit) as gpa, sum(e.unit) as unitTaken, cr.id as course_id from ENROLLMENT e "
				 +"join MASTER m on e.student_id = m.id "
				 +"join SECTION st on e.section_id = st.id "
				 +"join CLASS cs on st.class_id = cs.id "
				 +"join COURSE cr on cs.course_id = cr.id "
				 +"join MS_CONCENTRATION_COURSE mcc on mcc.course_id = cr.id "
				 +"join GRADE_CONVERSION gc on gc.letter_grade = e.grade "
				 +"where e.grade is not null and m.id = :student_id "
				 +"group by mcc.concentration_id,cr.id), "
				 +"student_unitSum as "
				 +"(select degree_unit.concentration_id, degree_unit.name, sum(student_unit.unitTaken) as studentUnitSum from " 
				 +"degree_unit left outer join student_unit "
				 +"on degree_unit.concentration_id = student_unit.concentration_id and degree_unit.course_id = student_unit.course_id "
				 +"where isnull(student_unit.gpa,0) >= degree_unit.min_gpa "
				 +"group by degree_unit.concentration_id, degree_unit.name ) "
				 
				 +"select student_unitSum.concentration_id, student_unitSum.name as concentration_name from student_unitSum "
				 +"join MS_CONCENTRATION_REQUIREMENT mcc on student_unitSum.concentration_id = mcc.concentration_id "  
				 +"join COURSE_CATEGORY_REQUIREMENT ccr on mcc.course_category_id = ccr.id "
				 +"where student_unitSum.studentUnitSum >= ccr.min_unit;" 
				)
				.setParameter("student_id", student_id)
				.setParameter("degree_id", degree_id)
				.getResultList();
		
		for (Object[] obj : rset){
			MSConcentration concentration = new MSConcentration();
			concentration.setId((Integer)obj[0]);
			concentration.setName((String)obj[1]);
			concentrationList.add(concentration);
		}
		return concentrationList;
	}

	@Override
	public List<Degree> getAllMasterDegree() {
		Session session = sessionFactory.getCurrentSession();
		List<Degree> degreeList = session.createQuery("from Degree d where d.degreeType.name = 'Master'",Degree.class).getResultList();
		return degreeList;
	}
	
	@Override
	public List<ConcentrationCourseClassDA> getConcentrationandCourseClassNotyetTakenbyStudentwithDegree(int student_id, int degree_id){
		Session session = sessionFactory.getCurrentSession();
		
		List<ConcentrationCourseClassDA> concentrationCourseClassList = new ArrayList<ConcentrationCourseClassDA>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery(
				 "with student_unit as "
			    +"(select  mcc.concentration_id, cs.course_id as course_id from ENROLLMENT e "
			    +" join SECTION st on e.section_id = st.id "
			    +" join CLASS cs on st.class_id = cs.id "
			    +" join MS_CONCENTRATION_COURSE mcc on mcc.course_id = cs.course_id "
			    +" where e.grade is not null and e.student_id = :student_id "
			    +" group by mcc.concentration_id, cs.course_id), "

				+"degree_unit as " 
				+"(select mc.id as degree_concentration_id, mc.name as concentration_name, mcc.course_id as degree_course_id from MS_CONCENTRATION mc " 
				+" join MS_CONCENTRATION_COURSE mcc on mc.id = mcc.concentration_id "
				+" where mc.degree_id = :degree_id ), " 
				+" student_degree_unit as "
				+"(select degree_unit.degree_concentration_id, degree_unit.degree_course_id from "
				+" degree_unit left outer join student_unit "
				+" on degree_unit.degree_concentration_id = student_unit.concentration_id and degree_unit.degree_course_id = student_unit.course_id "
				+" where student_unit.concentration_id is NULL) "

				+"select mcc.concentration_id, mc.name as concentration_name, cs.symbol, cu.currNum, qn.name as next_quarter, q.year as next_year from MS_CONCENTRATION_COURSE mcc " 
				+"join MS_CONCENTRATION mc on mcc.concentration_id = mc.id "
				+"left outer join student_degree_unit sdu on mcc.concentration_id = sdu.degree_concentration_id and mcc.course_id = sdu.degree_course_id "
				+"left outer join CLASS c on c.course_id = sdu.degree_course_id "
				+"left outer join COURSE cu on c.course_id = cu.id "
				+"left outer join COURSE_SUBJECT cs on cs.subject_id = cu.subject_id "
				+"left outer join QUARTER q on q.id = c.quarter_id " 
				+"left outer join QUARTER_NAME qn on q.name_id = qn.id "
				+"where (q.id is not null and (q.year > 2017 or (q.year=2017 and qn.name='FALL'))) or (cu.id is null)")
				.setParameter("student_id", student_id)
				.setParameter("degree_id", degree_id)
				.getResultList();
		
		int i = 0;
		for ( Object[] obj : rset){
			ConcentrationCourseClassDA concentrationCourseClass = new ConcentrationCourseClassDA();
			concentrationCourseClass.setConcentration(new MSConcentration());
			concentrationCourseClass.getConcentration().setName((String)obj[1]);
			concentrationCourseClass.setCourseClassList(new ArrayList<CourseClass>());
			
			if ( i==0 || (i > 0 && (!((String)obj[1]).equals(concentrationCourseClassList.get(i-1).getConcentration().getName()))) ){
				concentrationCourseClassList.add(concentrationCourseClass);
				i++;
			}
			
			if ((String)obj[2] != null){
				CourseClass courseClass = new CourseClass();
				courseClass.setCourse(new Course());
				courseClass.getCourse().setCourseSubject(new CourseSubject());
				courseClass.getCourse().getCourseSubject().setSymbol((String)obj[2]);
				courseClass.getCourse().setCourseUnitNumber(new CourseUnitNumber());
				courseClass.getCourse().getCourseUnitNumber().setCurrNum((String)obj[3]);
				courseClass.setQuarter(new Quarter());
				courseClass.getQuarter().setQuarterName(new QuarterName());
				courseClass.getQuarter().getQuarterName().setName((String)obj[4]);
				courseClass.getQuarter().setYear((Integer)obj[5]);	
				
				List<CourseClass> courseClassList = concentrationCourseClassList.get(concentrationCourseClassList.size()-1).getCourseClassList();
				courseClassList.add(courseClass);
			}
		}
		return concentrationCourseClassList;
	}
}
