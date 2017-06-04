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

@Entity
@Table(name="ENROLLMENT")
public class Enrollment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="student_id")
	private Student student;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="section_id")
	private Section section;
	
	@Column(name="letter_option")
	private boolean letter_option;
	
	@Column(name="su_option")
	private boolean su_option;
	
	@Column(name="grade")
	public String grade;
	
	@Column(name="unit")
	public int unitTaken;

	public Enrollment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public boolean isLetter_option() {
		return letter_option;
	}

	public void setLetter_option(boolean letter_option) {
		this.letter_option = letter_option;
	}

	public boolean isSu_option() {
		return su_option;
	}

	public void setSu_option(boolean su_option) {
		this.su_option = su_option;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getUnitTaken() {
		return unitTaken;
	}

	public void setUnitTaken(int unitTaken) {
		this.unitTaken = unitTaken;
	}
}
