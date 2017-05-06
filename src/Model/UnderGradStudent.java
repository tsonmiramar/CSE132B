package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="UNDERGRAD")
@PrimaryKeyJoinColumn(name="id")
public class UnderGradStudent extends Student{
	
	@Column(name="major")
	private String major;
	
	@Column(name="minor")
	private String minor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="college_id")
	private College college;

	public UnderGradStudent() {
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

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	
}
