package Model;

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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="major_id")
	private DEPARTMENT major;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="minor_id")
	private DEPARTMENT minor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="college_id")
	private College college;

	public UnderGradStudent() {
		super();
	}

	public DEPARTMENT getMajor() {
		return major;
	}

	public void setMajor(DEPARTMENT major) {
		this.major = major;
	}

	public DEPARTMENT getMinor() {
		return minor;
	}

	public void setMinor(DEPARTMENT minor) {
		this.minor = minor;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	
}
