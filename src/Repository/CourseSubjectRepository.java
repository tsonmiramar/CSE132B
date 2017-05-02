package Repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import IRepository.ICourseSubjectRepository;
import Model.CourseSubject;

@Repository
public class CourseSubjectRepository implements ICourseSubjectRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CourseSubject> getAllSubject() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<CourseSubject> query = session.createQuery("from CourseSubject",CourseSubject.class);
		List<CourseSubject> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<CourseSubject> getSubjectbyDepartment(int departmentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

