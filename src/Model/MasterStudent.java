package Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="MASTER")
@PrimaryKeyJoinColumn(name="id")
public class MasterStudent extends GradStudent{
	public MasterStudent(){super();}
}
