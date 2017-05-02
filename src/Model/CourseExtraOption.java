package Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseExtraOption {
	
	@Column(name="letter_option")
	private boolean letter_option;
	
	@Column(name="SU_option")
	private boolean SU_option;
	
	@Column(name="labwork")
	private boolean labwork;
	
	@Column(name="instructor_consent")
	private boolean instructor_consent;
	
	public CourseExtraOption(){}
	
	public boolean isLetter_option() {
		return letter_option;
	}
	public void setLetter_option(boolean letter_option) {
		this.letter_option = letter_option;
	}
	public boolean isSU_option() {
		return SU_option;
	}
	public void setSU_option(boolean sU_option) {
		SU_option = sU_option;
	}
	public boolean isLabwork() {
		return labwork;
	}
	public void setLabwork(boolean labwork) {
		this.labwork = labwork;
	}
	public boolean isInstructor_consent() {
		return instructor_consent;
	}
	public void setInstructor_consent(boolean instructor_consent) {
		this.instructor_consent = instructor_consent;
	}

	@Override
	public String toString() {
		return "CourseExtraOption [letter_option=" + letter_option + ", SU_option=" + SU_option + ", labwork=" + labwork
				+ ", instructor_consent=" + instructor_consent + "]";
	}
	
	
}
