package Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="UNDERGRAD")
@PrimaryKeyJoinColumn(name="id")
public class UnderGradStudent extends Student{
	
	private String major;
	private String minor;
	private String college;
}
