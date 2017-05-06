package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import IRepository.ICollegeRepository;
import Model.College;

@Repository
public class CollegeRepository extends BaseRepository implements ICollegeRepository {
	
	@Override
	public List<College> getAllCollege() {
		Session session = this.sessionFactory.getCurrentSession();
		Query<College> query = session.createQuery("from College",College.class);
		List<College> collegeList = query.getResultList();
		return collegeList;
	}

}
