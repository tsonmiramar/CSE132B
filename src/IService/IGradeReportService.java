package IService;

import java.math.BigDecimal;
import java.util.List;

import Model.GradeCount;

public interface IGradeReportService {

	List<GradeCount> getGradeCountbyFacultyCourseQuarter(int faculty_id, int course_id, int quarter_id);

	List<GradeCount> getGradeCountbyFacultyCourse(int faculty_id, int course_id);

	List<GradeCount> getGradeCountbyCourse(int course_id);

	List<BigDecimal> getGradeGPAFacultyCourse(int faculty_id, int course_id);

}
