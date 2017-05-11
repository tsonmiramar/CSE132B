package Repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IDegreeRepository;
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

}
