package Repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IStudentRepository;
import Model.Probation;
import Model.Student;
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

	@Override
	public List<Student> getAllStudent() {
		Session session = sessionFactory.getCurrentSession();
		List<Student> studentList = session.createQuery("from Student",Student.class)
										   .getResultList();
		return studentList;
	}

	@Override
	public void insertProbation(List<Probation> probation) {
		Session session = sessionFactory.getCurrentSession();
		for ( Probation probationEntry : probation){
			session.save(probationEntry);
		}
	}
}
