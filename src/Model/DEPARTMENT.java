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


@Entity
@Table(name= "DEPARTMENT")
public class DEPARTMENT {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
	private Collection<CourseSubject> subjectList = new HashSet<CourseSubject>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
	private Collection<Faculty> facultyList = new HashSet<Faculty>();
	
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

}
