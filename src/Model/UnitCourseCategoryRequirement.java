package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="UNIT_COURSE_CATEGORY")
public class UnitCourseCategoryRequirement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int	id;
	
	@ManyToOne
	@JoinColumn(name="unit_category_id")
	UnitCategory unitCategory;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="course_category_id")
	CourseCategoryRequirement courseCategoryRequirement;

	public UnitCourseCategoryRequirement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UnitCategory getUnitCategory() {
		return unitCategory;
	}

	public void setUnitCategory(UnitCategory unitCategory) {
		this.unitCategory = unitCategory;
	}

	public CourseCategoryRequirement getCourseCategoryRequirement() {
		return courseCategoryRequirement;
	}

	public void setCourseCategoryRequirement(CourseCategoryRequirement courseCategoryRequirement) {
		this.courseCategoryRequirement = courseCategoryRequirement;
	}
}
