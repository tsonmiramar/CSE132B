package Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="PHD_CANDIDATE")
@PrimaryKeyJoinColumn(name="id")
public class PhDCandidate extends PhDStudent{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="COMMITTEE_PHD",
		joinColumns = {@JoinColumn(name = "phd_candidate_id")},
		inverseJoinColumns = {@JoinColumn(name = "committee_id")}
	)
	private Committee phdCommittee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="advisor_id")
	private Faculty advisor;
	
	public PhDCandidate(){super();}

	public Committee getPhdCommittee() {
		return phdCommittee;
	}

	public void setPhdCommittee(Committee phdCommittee) {
		this.phdCommittee = phdCommittee;
	}

	public Faculty getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Faculty advisor) {
		this.advisor = advisor;
	}
}
