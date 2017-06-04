package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import IRepository.IFacultyRespository;
import Model.Faculty;

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
		session.createNativeQuery("insert into FACULTY values (:name,:title,:dept_id)")
				.setParameter("name", faculty.getName())
				.setParameter("title", faculty.getTitle())
				.setParameter("dept_id", faculty.getDepartment().getId())
				.executeUpdate();
	}

	@Override
	public List<Faculty> getAllFacultyByDepartmentId(int dept_id) {
		Session session = sessionFactory.getCurrentSession();
		List<Faculty> facultyList = session
									.createQuery("select f from Faculty f where f.department.id=:dept_id",Faculty.class)
									.setParameter("dept_id", dept_id)
									.getResultList();		
		return facultyList;
	}

}
