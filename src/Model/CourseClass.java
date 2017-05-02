package Model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.hibernate.annotations.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLASS")
@Getter
@Setter
public class CourseClass {
	
	@GenericGenerator(name="gen", strategy="foreign", 
			parameters={@Parameter(name="property", value="course")})
	@Id
	@Column(name="id", unique=true, nullable=false)
	@GeneratedValue(generator="gen")
	private int id;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Course course;
	
	@Column(name="title")
	private String title;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="CLASS_QUARTER",
			joinColumns = { @JoinColumn(name = "class_id", nullable = false)},
			inverseJoinColumns = { @JoinColumn(name = "quarter_id", nullable = false)}
			)
	private Collection<Quarter> quarterList = new HashSet<Quarter>();

	@OneToMany(fetch=FetchType.LAZY, mappedBy="sectionClass", cascade=CascadeType.ALL)
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

	public Collection<Quarter> getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(Collection<Quarter> quarterList) {
		this.quarterList = quarterList;
	}

	public Collection<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(Collection<Section> sectionList) {
		this.sectionList = sectionList;
	}
}
