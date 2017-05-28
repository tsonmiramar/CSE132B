package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COURSE_TYPE_CATEGORY")
public class CourseTypeCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course courseWithType;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private CourseType courseType;
	
	public CourseTypeCategory() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourseWithType() {
		return courseWithType;
	}

	public void setCourseWithType(Course courseWithType) {
		this.courseWithType = courseWithType;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
}

