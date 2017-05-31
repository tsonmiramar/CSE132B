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
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="degree_id")
	private Degree degreeMaster;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
