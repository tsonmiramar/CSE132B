package Model;

import java.util.List;

public class ConcentrationCourseClassDA {
	
	private MSConcentration concentration;
	private List<CourseClass> courseClassList;
	
	public ConcentrationCourseClassDA() {
		super();
	}
	
	public MSConcentration getConcentration() {
		return concentration;
	}
	public void setConcentration(MSConcentration concentration) {
		this.concentration = concentration;
	}

	public List<CourseClass> getCourseClassList() {
		return courseClassList;
	}

	public void setCourseClassList(List<CourseClass> courseClassList) {
		this.courseClassList = courseClassList;
	}
}
