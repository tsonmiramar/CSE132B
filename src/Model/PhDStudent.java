package Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="PHD")
@PrimaryKeyJoinColumn(name="id")
public class PhDStudent extends GradStudent{

	public PhDStudent(){super();};
}
