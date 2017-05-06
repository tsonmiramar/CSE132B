package Repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IStudentRepository;
import Model.StudentType;

@Repository
public class StudentRepository extends BaseRepository implements IStudentRepository{
	
	@Override
	public void insertStudent(StudentType studentType) {
		Session session = sessionFactory.getCurrentSession();
		
		if ( studentType.getUnderGrad() != null){
			session.save(studentType.getUnderGrad());
		}
		else if ( studentType.getMaster() != null){
			session.save(studentType.getMaster());
		}
		else if ( studentType.getBsmsMaster() != null){
			session.save(studentType.getBsmsMaster());
		}
		else if ( studentType.getPhdCandidate() != null){
			session.save(studentType.getPhdCandidate());
		}
		else if ( studentType.getPhdPreCandidate() != null){
			session.save(studentType.getPhdPreCandidate());
		}
	}
}
