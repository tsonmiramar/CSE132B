package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="FACULTY")
public class Faculty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="dept_id")
	private DEPARTMENT department;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="faculty")
	private Collection<Section> sectionTeachList = new HashSet<Section>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="advisor")
	private Collection<PhDCandidate> phdAdvisingList = new HashSet<PhDCandidate>();
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="COMMITTEE_FACULTY",
				joinColumns={ @JoinColumn(name="faculty_id") },
				inverseJoinColumns = { @JoinColumn(name="committee_id") }
			)
	private Committee facultyCommittee;
	
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DEPARTMENT getDepartment() {
		return department;
	}

	public void setDepartment(DEPARTMENT department) {
		this.department = department;
	}

	public Collection<Section> getSectionTeachList() {
		return sectionTeachList;
	}

	public void setSectionTeachList(Collection<Section> sectionTeachList) {
		this.sectionTeachList = sectionTeachList;
	}

	public Committee getFacultyCommittee() {
		return facultyCommittee;
	}

	public void setFacultyCommittee(Committee facultyCommittee) {
		this.facultyCommittee = facultyCommittee;
	}
}
