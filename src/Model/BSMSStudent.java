package Model;

import javax.persistence.Column;
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
	
	@Column(name="major")
	private String major;
	
	@Column(name="minor")
	private String minor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="college_id")
	private College collegeBSMS;

	public BSMSStudent() {
		super();
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public College getCollegeBSMS() {
		return collegeBSMS;
	}

	public void setCollegeBSMS(College collegeBSMS) {
		this.collegeBSMS = collegeBSMS;
	}
}
