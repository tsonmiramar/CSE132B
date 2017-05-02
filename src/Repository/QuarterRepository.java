package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import IRepository.IQuarterRepository;
import Model.Quarter;

@Repository
public class QuarterRepository implements IQuarterRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Quarter> getAllQuarter() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Quarter> query = session.createQuery("from Quarter",Quarter.class);
		
		List<Quarter> quarterList = query.getResultList();
		return quarterList;
	}
}
