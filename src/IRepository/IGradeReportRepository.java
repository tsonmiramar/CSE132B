package IRepository;

import java.math.BigDecimal;
import java.util.List;

import Model.GradeCount;

public interface IGradeReportRepository {
	
	List<GradeCount> getGradeCountbyCourseFacultyQuarter(int course_id, int faculty_id, int quarter_id);

	List<GradeCount> getGradeCountbyCourseFaculty(int faculty_id, int course_id);

	List<GradeCount> getGradeCountbyCourse(int course_id);

	List<BigDecimal> getGradeGPAFacultyCourse(int faculty_id, int course_id);
}
