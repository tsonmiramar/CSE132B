package Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.IStudentRepository;
import Model.DEPARTMENT;
import Model.GradStudent;
import Model.PhDCandidate;
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
		List<Student> studentList = new ArrayList<Student>();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery("select s.id,s.firstname,s.lastname,s.middlename from STUDENT s")
										   .getResultList();
		for ( Object[] obj : rset){
			Student student = new Student();
			student.setId((Integer)obj[0]);
			student.setFirstname((String)obj[1]);
			student.setLastname((String)obj[2]);
			student.setMiddlename((String)obj[3]);
			
			studentList.add(student);
		}
		return studentList;
	}

	@Override
	public void insertProbation(List<Probation> probation) {
		Session session = sessionFactory.getCurrentSession();
		for ( Probation probationEntry : probation){
			session.save(probationEntry);
		}
	}

	@Override
	public List<PhDCandidate> getAllPhDCandidate() {
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery("select s.id, s.firstname, s.lastname, s.middlename, d.id as dept_id, d.name as dept_name from PHD_CANDIDATE p "
													  + "join STUDENT s on p.id = s.id "
													  + "join GRAD g on p.id = g.id "
													  + "join DEPARTMENT d on g.dept_id = d.id; ").getResultList();
		
		List<PhDCandidate> studentList = new ArrayList<PhDCandidate>();
		
		for ( Object[] obj : rset){
			PhDCandidate student = new PhDCandidate();
			student.setId((Integer)obj[0]);
			student.setFirstname((String)obj[1]);
			student.setLastname((String)obj[2]);
			student.setMiddlename((String) obj[3]);
			student.setDepartment(new DEPARTMENT());
			student.getDepartment().setId((Integer) obj[4]);
			student.getDepartment().setName((String) obj[5]);
			
			studentList.add(student);
		}
		return studentList;
	}

	@Override
	public List<GradStudent> getAllGradStudent() {
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Object[]> rset = session.createNativeQuery("select s.id, s.firstname, s.lastname, s.middlename, d.id as dept_id, d.name as dept_name from GRAD g "
													   +"join STUDENT s on g.id = s.id "
													   +"join DEPARTMENT d on g.dept_id = d.id").getResultList();
		
		List<GradStudent> studentList = new ArrayList<GradStudent>();
		
		for ( Object[] obj : rset){
			GradStudent student = new GradStudent();
			student.setId((Integer)obj[0]);
			student.setFirstname((String)obj[1]);
			student.setLastname((String)obj[2]);
			student.setMiddlename((String) obj[3]);
			student.setDepartment(new DEPARTMENT());
			student.getDepartment().setId((Integer) obj[4]);
			student.getDepartment().setName((String) obj[5]);
			
			studentList.add(student);
		}
		return studentList;
	}
}
