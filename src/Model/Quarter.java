package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="QUARTER")
public class Quarter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="name_id", updatable=false)
	private QuarterName quarterName;
	
	@Column(name="year", updatable=false)
	private int year;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="quarterList")
	private Collection<CourseClass> classList = new HashSet<CourseClass>();
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="quarterAttendList", cascade=CascadeType.ALL)
	private Collection<Student> studentAttendList = new HashSet<Student>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="quarterProbation")
	private Collection<Probation> probation = new HashSet<Probation>();
	
	public Quarter() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuarterName getQuarterName() {
		return quarterName;
	}

	public void setQuarterName(QuarterName quarterName) {
		this.quarterName = quarterName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Collection<CourseClass> getClassList() {
		return classList;
	}

	public void setClassList(Collection<CourseClass> classList) {
		this.classList = classList;
	}

	public Collection<Student> getStudentAttendList() {
		return studentAttendList;
	}

	public void setStudentAttendList(Collection<Student> studentAttendList) {
		this.studentAttendList = studentAttendList;
	}

	public Collection<Probation> getProbation() {
		return probation;
	}

	public void setProbation(Collection<Probation> probation) {
		this.probation = probation;
	}
}
