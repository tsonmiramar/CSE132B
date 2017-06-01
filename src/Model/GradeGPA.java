package Model;

import java.math.BigDecimal;

public class GradeGPA {
	
	private String grade;
	private BigDecimal gpa;
	public GradeGPA(String grade, BigDecimal gpa) {
		super();
		this.grade = grade;
		this.gpa = gpa;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public BigDecimal getGpa() {
		return gpa;
	}
	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
	}
}
