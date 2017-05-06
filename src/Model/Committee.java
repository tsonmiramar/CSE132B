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
@Table(name="COMMITTEE")
public class Committee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="facultyCommittee")
	private Collection<Faculty> facultyList = new HashSet<Faculty>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="gradCommittee")
	private Collection<GradStudent> gradList = new HashSet<GradStudent>();
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="phdCommittee")
	private Collection<PhDCandidate> phdList = new HashSet<PhDCandidate>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<Faculty> getFacultyList() {
		return facultyList;
	}

	public void setFacultyList(Collection<Faculty> facultyList) {
		this.facultyList = facultyList;
	}

	public Collection<GradStudent> getGradList() {
		return gradList;
	}

	public void setGradList(Collection<GradStudent> gradList) {
		this.gradList = gradList;
	}

	public Collection<PhDCandidate> getPhdList() {
		return phdList;
	}

	public void setPhdList(Collection<PhDCandidate> phdList) {
		this.phdList = phdList;
	}	

}
