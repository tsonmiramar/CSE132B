package Model;

public class StudentType {
	private UnderGradStudent underGrad;
	private MasterStudent master;
	private BSMSStudent	bsmsMaster;
	private PhDCandidate phdCandidate;
	private PhDPreCandidate phdPreCandidate;
	
	public StudentType() {
		super();
	}
	public UnderGradStudent getUnderGrad() {
		return underGrad;
	}
	public void setUnderGrad(UnderGradStudent underGrad) {
		this.underGrad = underGrad;
	}
	public MasterStudent getMaster() {
		return master;
	}
	public void setMaster(MasterStudent master) {
		this.master = master;
	}
	public PhDCandidate getPhdCandidate() {
		return phdCandidate;
	}
	public void setPhdCandidate(PhDCandidate phdCandidate) {
		this.phdCandidate = phdCandidate;
	}
	public PhDPreCandidate getPhdPreCandidate() {
		return phdPreCandidate;
	}
	public void setPhdPreCandidate(PhDPreCandidate phdPreCandidate) {
		this.phdPreCandidate = phdPreCandidate;
	}
	public BSMSStudent getBsmsMaster() {
		return bsmsMaster;
	}
	public void setBsmsMaster(BSMSStudent bsmsMaster) {
		this.bsmsMaster = bsmsMaster;
	}
}
