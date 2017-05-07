package Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import IRepository.IFacultyRespository;
import Model.Faculty;
import Repository.BaseRepository;

@Repository
public class FacultyRepository extends BaseRepository implements IFacultyRespository {
	
	@Override
	public List<Faculty> getAllFaculty() {
		Session session = sessionFactory.getCurrentSession();
	
		Query<Faculty> query = session.createQuery("from Faculty",Faculty.class);
		
		List<Faculty> facultyList = query.getResultList();
		
		return facultyList;
	}

	@Override
	public void insertFaculty(Faculty faculty) {
		Session session = sessionFactory.getCurrentSession();
		session.save(faculty);
	}

}
