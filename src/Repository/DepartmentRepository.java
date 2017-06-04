package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import IRepository.IDepartmentRepository;
import Model.DEPARTMENT;

@Repository
public class DepartmentRepository extends BaseRepository implements IDepartmentRepository{
	
	@Override
	@Transactional
	public List<DEPARTMENT> getAllDepartment() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<DEPARTMENT> query = session.createQuery("from DEPARTMENT", DEPARTMENT.class);
		
		List<DEPARTMENT> deptList = query.getResultList();
		return deptList;
	}
	

}
