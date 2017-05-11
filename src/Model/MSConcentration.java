package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MS_CONCENTRATION")
public class MSConcentration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int	id;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private Degree degreeMaster;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course courseConcentration;

	public MSConcentration() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Degree getDegreeMaster() {
		return degreeMaster;
	}

	public void setDegreeMaster(Degree degreeMaster) {
		this.degreeMaster = degreeMaster;
	}

	public Course getCourseConcentration() {
		return courseConcentration;
	}

	public void setCourseConcentration(Course courseConcentration) {
		this.courseConcentration = courseConcentration;
	}
}
