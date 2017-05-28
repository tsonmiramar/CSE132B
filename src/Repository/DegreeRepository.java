package Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IDegreeRepository;
import Model.Degree;
import Model.DegreeType;
import Model.MSConcentration;
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

}
