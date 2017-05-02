package Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import IRepository.IFacultyRespository;
import Model.Faculty;

@Repository
public class FacultyRepository implements IFacultyRespository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Faculty> getAllFaculty() {
		Session session = sessionFactory.getCurrentSession();
	
		Query<Faculty> query = session.createQuery("from Faculty",Faculty.class);
		
		List<Faculty> facultyList = query.getResultList();
		
		return facultyList;
	}

}
