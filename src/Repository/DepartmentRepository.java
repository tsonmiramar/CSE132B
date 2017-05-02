package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import IRepository.IDepartmentRepository;
import Model.DEPARTMENT;

@Repository
public class DepartmentRepository implements IDepartmentRepository{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<DEPARTMENT> getAllDepartment() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<DEPARTMENT> query = session.createQuery("from DEPARTMENT", DEPARTMENT.class);
		
		List<DEPARTMENT> deptList = query.getResultList();
		return deptList;
	}
	

}
