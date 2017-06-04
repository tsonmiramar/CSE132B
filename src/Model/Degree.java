package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="DEGREE")
public class Degree {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int	id;
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private DegreeType degreeType;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy="degreeMaster")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Collection<MSConcentration> concentrationCourseList = new HashSet<MSConcentration>();
	
	public Degree() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DegreeType getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(DegreeType degreeType) {
		this.degreeType = degreeType;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<MSConcentration> getConcentrationCourseList() {
		return concentrationCourseList;
	}

	public void setConcentrationCourseList(Collection<MSConcentration> concentrationCourseList) {
		this.concentrationCourseList = concentrationCourseList;
	}
}
