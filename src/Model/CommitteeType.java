package Model;

public class CommitteeType {
	
	private Committee gradCommittee;
	private Committee phDCommittee;
	
	public CommitteeType() {
		super();
	}
	public Committee getGradCommittee() {
		return gradCommittee;
	}
	public void setGradCommittee(Committee gradCommittee) {
		this.gradCommittee = gradCommittee;
	}
	public Committee getPhDCommittee() {
		return phDCommittee;
	}
	public void setPhDCommittee(Committee phDCommittee) {
		this.phDCommittee = phDCommittee;
	}
}
