package Model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="PHD_PRECANDIDATE")
@PrimaryKeyJoinColumn(name="id")
public class PhDPreCandidate extends PhDStudent{

	public PhDPreCandidate() {
		super();
	}
}
