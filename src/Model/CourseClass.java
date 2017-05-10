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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLASS")
@Getter
@Setter
public class CourseClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="quarter_id")
	private Quarter quarter;

	@OneToMany( fetch=FetchType.EAGER, mappedBy="sectionClass", cascade=CascadeType.ALL)
	private Collection<Section> sectionList = new HashSet<Section>();
	
	public CourseClass() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Quarter getQuarter() {
		return quarter;
	}

	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}

	public Collection<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(Collection<Section> sectionList) {
		this.sectionList = sectionList;
	}
}
