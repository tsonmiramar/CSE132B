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
@Table(name="PROBATION_PERIOD")
public class Probation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student studentProbation;
	
	@ManyToOne
	@JoinColumn(name="quarter_id")
	private Quarter quarterProbation;
	
	@Column(name="reason")
	private String reason;

	public Probation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudentProbation() {
		return studentProbation;
	}

	public void setStudentProbation(Student studentProbation) {
		this.studentProbation = studentProbation;
	}

	public Quarter getQuarterProbation() {
		return quarterProbation;
	}

	public void setQuarterProbation(Quarter quarterProbation) {
		this.quarterProbation = quarterProbation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
