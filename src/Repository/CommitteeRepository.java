package Repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import IRepository.ICommitteeRepository;
import Model.Committee;
import Model.Faculty;
import Model.GradStudent;
import Model.PhDCandidate;

@Repository
public class CommitteeRepository extends BaseRepository implements ICommitteeRepository{

	@Override
	public void insertCommittee(Committee committee) {
		Session session = sessionFactory.getCurrentSession();
			
		int committee_id = (Integer)session.save(committee);
		
		for ( Faculty faculty : committee.getFacultyList()){
			session.createNativeQuery("insert into COMMITTEE_FACULTY values (:committee, :faculty)")
			.setParameter("committee", committee_id)
			.setParameter("faculty", faculty.getId())
			.executeUpdate();
		}
		
		int student_id = !committee.getGradList().isEmpty() ?
				((List<GradStudent>) committee.getGradList()).get(0).getId() :
				((List<PhDCandidate>) committee.getPhdList()).get(0).getId();
				
		String table = !committee.getGradList().isEmpty()? "COMMITTEE_GRAD" : "COMMITTEE_PHD";
		
		session.createNativeQuery("insert into "+table+" values (:committee,:student)")
		.setParameter("student", student_id)
		.setParameter("committee", committee_id)
		.executeUpdate();
		
	}
	
}
