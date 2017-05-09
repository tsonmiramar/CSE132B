package Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="BSMS_STUDENT")
@PrimaryKeyJoinColumn(name="id")
public class BSMSStudent extends MasterStudent{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="major_id")
	private DEPARTMENT majorBSMS;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="minor_id")
	private DEPARTMENT minorBSMS;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="college_id")
	private College collegeBSMS;

	public BSMSStudent() {
		super();
	}

	public DEPARTMENT getMajorBSMS() {
		return majorBSMS;
	}

	public void setMajorBSMS(DEPARTMENT majorBSMS) {
		this.majorBSMS = majorBSMS;
	}

	public DEPARTMENT getMinorBSMS() {
		return minorBSMS;
	}

	public void setMinorBSMS(DEPARTMENT minorBSMS) {
		this.minorBSMS = minorBSMS;
	}

	public College getCollegeBSMS() {
		return collegeBSMS;
	}

	public void setCollegeBSMS(College collegeBSMS) {
		this.collegeBSMS = collegeBSMS;
	}
}
