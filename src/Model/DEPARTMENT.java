package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name= "DEPARTMENT")
public class DEPARTMENT {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
	private Collection<CourseSubject> subjectList = new HashSet<CourseSubject>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
	private Collection<Faculty> facultyList = new HashSet<Faculty>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="major")
	private Collection<UnderGradStudent> studentMajorList = new HashSet<UnderGradStudent>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="minor")
	private Collection<UnderGradStudent> studentMinorList = new HashSet<UnderGradStudent>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="majorBSMS")
	private Collection<BSMSStudent> bsmsMajorList = new HashSet<BSMSStudent>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="minorBSMS")
	private Collection<BSMSStudent> bsmsMinorList = new HashSet<BSMSStudent>();
	
	public DEPARTMENT(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<CourseSubject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(Collection<CourseSubject> subjectList) {
		this.subjectList = subjectList;
	}

	public Collection<Faculty> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(Collection<Faculty> facultyList) {
		this.facultyList = facultyList;
	}

	public Collection<UnderGradStudent> getStudentMajorList() {
		return studentMajorList;
	}

	public void setStudentMajorList(Collection<UnderGradStudent> studentMajorList) {
		this.studentMajorList = studentMajorList;
	}

	public Collection<UnderGradStudent> getStudentMinorList() {
		return studentMinorList;
	}

	public void setStudentMinorList(Collection<UnderGradStudent> studentMinorList) {
		this.studentMinorList = studentMinorList;
	}

	public Collection<BSMSStudent> getBsmsMajorList() {
		return bsmsMajorList;
	}

	public void setBsmsMajorList(Collection<BSMSStudent> bsmsMajorList) {
		this.bsmsMajorList = bsmsMajorList;
	}

	public Collection<BSMSStudent> getBsmsMinorList() {
		return bsmsMinorList;
	}

	public void setBsmsMinorList(Collection<BSMSStudent> bsmsMinorList) {
		this.bsmsMinorList = bsmsMinorList;
	}
}
