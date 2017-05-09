package Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class CourseUnitNumber {
	
	@Column(name="currNum")
	private String currNum;
	
	@JsonIgnore
	@Column(name="prevNum")
	private String prevNum;
		
	@Column(name="unitFrom")
	private int unitFrom;
	
	@Column(name="unitTo")
	private int unitTo;
	
	public CourseUnitNumber(){}
	
	public String getCurrNum() {
		return currNum;
	}
	public void setCurrNum(String currNum) {
		this.currNum = currNum;
	}
	public String getPrevNum() {
		return prevNum;
	}
	public void setPrevNum(String prevNum) {
		this.prevNum = prevNum;
	}
	public int getUnitFrom() {
		return unitFrom;
	}
	public void setUnitFrom(int unitFrom) {
		this.unitFrom = unitFrom;
	}
	public int getUnitTo() {
		return unitTo;
	}
	public void setUnitTo(int unitTo) {
		this.unitTo = unitTo;
	}

	@Override
	public String toString() {
		return "CourseUnitNumber [currNum=" + currNum + ", prevNum=" + prevNum + ", unitFrom=" + unitFrom + ", unitTo="
				+ unitTo + "]";
	}
}
