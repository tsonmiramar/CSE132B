package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="STUDENT")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="pid")
	private int pid;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="middlename")
	private String middlename;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="ssn")
	private int ssn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resident_status")
	private ResidentStatus residentStatus;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name="ATTENDANCE",
				joinColumns={@JoinColumn(name="student_id", nullable=false, updatable=false)},
				inverseJoinColumns={@JoinColumn(name="quarter_id", nullable=false, updatable=false)}
			)
	private Collection<Quarter> quarterAttendList = new HashSet<Quarter>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="studentProbation")
	private Collection<Probation> probation = new HashSet<Probation>();
	
	public Student(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public ResidentStatus getResidentStatus() {
		return residentStatus;
	}

	public void setResidentStatus(ResidentStatus residentStatus) {
		this.residentStatus = residentStatus;
	}

	public Collection<Quarter> getQuarterAttendList() {
		return quarterAttendList;
	}

	public void setQuarterAttendList(Collection<Quarter> quarterAttendList) {
		this.quarterAttendList = quarterAttendList;
	}

	public Collection<Probation> getProbation() {
		return probation;
	}

	public void setProbation(Collection<Probation> probation) {
		this.probation = probation;
	}
}
