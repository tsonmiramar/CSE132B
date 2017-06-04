package Repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IWeekdayRepository;
import Model.Weekday;

@Repository
public class WeekdayRepository extends BaseRepository implements IWeekdayRepository {

	@Override
	public List<Weekday> getAllWeekDay() {	
		Session session = sessionFactory.getCurrentSession();
		List<Weekday> weekdayList = session.createQuery("from Weekday",Weekday.class).getResultList();
		return weekdayList;
	}

}
