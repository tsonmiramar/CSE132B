package Model;

import java.util.*;
import javax.persistence.*;

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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dept_id")
	private DEPARTMENT department;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="faculty")
	private Collection<Section> sectionTeachList = new HashSet<Section>();

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
}
