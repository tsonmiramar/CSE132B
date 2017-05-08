package Model;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Embedded;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="COURSE")
@Inheritance(strategy = InheritanceType.JOINED)
@Check(constraints="letter_option != 0 OR SU_option != 0")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subject_id")
	private CourseSubject courseSubject;
	
	@JsonIgnore
	@Embedded
	private CourseExtraOption courseOption;
	
	@Embedded
	private CourseUnitNumber courseUnitNumber;
	
	@JsonIgnore
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name="PREREQUISITE", 
				joinColumns = { @JoinColumn(name = "course_id", nullable = false)},
				inverseJoinColumns = { @JoinColumn(name = "prereq_id", nullable = false)}
			  )
	private Collection<Course> prereq = new HashSet<Course>(); //List of all prerequisite of this course
	
	@JsonIgnore
	@ManyToMany(mappedBy="prereq", fetch= FetchType.LAZY)
	private Collection<Course> prereqOf = new HashSet<Course>(); //List of all course having this course as prerequisite
	
	@JsonIgnore
	@OneToOne(mappedBy="course")
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private CourseClass courseClass;
	
	public Course() {}

	public int getId() {
		return id;
	}
	
	public Collection<Course> getPrereqOf() {
		return prereqOf;
	}
	
	public void setPrereqOf(Collection<Course> prereqOf) {
		this.prereqOf = prereqOf;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public CourseSubject getCourseSubject() {
		return courseSubject;
	}

	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}

	public CourseUnitNumber getCourseUnitNumber() {
		return courseUnitNumber;
	}
	public void setCourseUnitNumber(CourseUnitNumber courseUnitNumber) {
		this.courseUnitNumber = courseUnitNumber;
	}
	public CourseExtraOption getCourseOption() {
		return courseOption;
	}
	public void setCourseOption(CourseExtraOption courseOption) {
		this.courseOption = courseOption;
	}
	
	public Collection<Course> getPrereq() {
		return prereq;
	}
	public void setPrereq(Collection<Course> prereq) {
		this.prereq = prereq;
	}

	public CourseClass getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(CourseClass courseClass) {
		this.courseClass = courseClass;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseSubject=" + courseSubject.toString() + ", courseOption=" + courseOption.toString()
				+ ", courseUnitNumber=" + courseUnitNumber + ", prereq=" + prereq + ", prereqOf=" + prereqOf + "]";
	}
	
	
}
