package Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import IRepository.IQuarterRepository;
import Model.Quarter;
import Model.QuarterName;

@Repository
public class QuarterRepository extends BaseRepository implements IQuarterRepository{

	@Override
	public List<Quarter> getAllQuarter() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Quarter> query = session.createQuery("from Quarter",Quarter.class);
		
		List<Quarter> quarterList = query.getResultList();
		return quarterList;
	}

	@Override
	public List<QuarterName> getAllQuarterName() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<QuarterName> query = session.createQuery("from QuarterName",QuarterName.class);
		
		List<QuarterName> quarterNameList = query.getResultList();
		
		return quarterNameList;
	}
}
