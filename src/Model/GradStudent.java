package Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="GRAD")
@PrimaryKeyJoinColumn(name="id")
public class GradStudent extends Student{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id",updatable=false)
	private DEPARTMENT department;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="COMMITTEE_GRAD",
			joinColumns = {@JoinColumn(name = "grad_id")},
	        inverseJoinColumns = {@JoinColumn(name = "committee_id")}
	)
	private Committee gradCommittee;
	
	public GradStudent() {
		super();
	}

	public DEPARTMENT getDepartment() {
		return department;
	}

	public void setDepartment(DEPARTMENT department) {
		this.department = department;
	}

	public Committee getGradCommittee() {
		return gradCommittee;
	}

	public void setGradCommittee(Committee gradCommittee) {
		this.gradCommittee = gradCommittee;
	}
}
