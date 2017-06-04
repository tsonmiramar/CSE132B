package Repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRepository {
	@Autowired
	protected SessionFactory sessionFactory;
}
