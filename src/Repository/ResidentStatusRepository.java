package Repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IResidentStatusRepository;
import Model.ResidentStatus;

@Repository
public class ResidentStatusRepository extends BaseRepository implements IResidentStatusRepository {

	@Override
	public List<ResidentStatus> getAllResidentStatus() {
		Session session = sessionFactory.getCurrentSession();
		List<ResidentStatus> residentStatusList = session
							.createQuery("from ResidentStatus",ResidentStatus.class)
							.getResultList();
		return residentStatusList;
	}

}
