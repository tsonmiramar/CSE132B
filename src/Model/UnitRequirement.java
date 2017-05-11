package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="UNIT_REQUIREMENT")
public class UnitRequirement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private DEPARTMENT degreeDepartment;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="degree_id")
	private Degree degree;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="totalunit_id")
	UnitCategory totalUnit;

	public UnitRequirement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DEPARTMENT getDegreeDepartment() {
		return degreeDepartment;
	}

	public void setDegreeDepartment(DEPARTMENT degreeDepartment) {
		this.degreeDepartment = degreeDepartment;
	}

	public UnitCategory getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(UnitCategory totalUnit) {
		this.totalUnit = totalUnit;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}
}
