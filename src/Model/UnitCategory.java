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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="UNIT_CATEGORY")
public class UnitCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int	id;
	
	@Column(name="totalunit")
	private int unitNumber;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="unitCategory")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Collection<UnitCourseCategoryRequirement> unitCourseCategoryRequirement = new HashSet<UnitCourseCategoryRequirement>();
	public UnitCategory() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Collection<UnitCourseCategoryRequirement> getUnitCourseCategoryRequirement() {
		return unitCourseCategoryRequirement;
	}

	public void setUnitCourseCategoryRequirement(Collection<UnitCourseCategoryRequirement> unitCourseCategoryRequirement) {
		this.unitCourseCategoryRequirement = unitCourseCategoryRequirement;
	}
}
