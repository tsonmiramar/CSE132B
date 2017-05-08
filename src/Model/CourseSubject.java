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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="COURSE_SUBJECT")
public class CourseSubject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subject_id")
	private int subject_id;
	
	@Column(name="symbol")
	private String symbol;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dept_id", referencedColumnName="id", nullable=false)
	private DEPARTMENT department;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courseSubject")
	private Collection<Course> courseList = new HashSet<Course>();
	
	public CourseSubject(){};
	
	public DEPARTMENT getDepartment() {
		return department;
	}

	public void setDepartment(DEPARTMENT department) {
		this.department = department;
	}



	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public Collection<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(Collection<Course> courseList) {
		this.courseList = courseList;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "CourseSubject [subject_id=" + subject_id + ", symbol=" + symbol + "]";
	}
}
