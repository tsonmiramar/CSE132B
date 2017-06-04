package Model;

import java.math.BigDecimal;

public class QuarterGPA_DAO {
	
	private BigDecimal gpa;
	private Quarter quarter;
	
	public QuarterGPA_DAO() {
		super();
	}
	
	public BigDecimal getGpa() {
		return gpa;
	}

	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
	}

	public Quarter getQuarter() {
		return quarter;
	}
	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}
	
	
}
