package IService;

import java.util.List;

import Model.GradeCount;
import Model.GradeGPA;

public interface IGradeReportService {

	List<GradeCount> getGradeCountbyFacultyCourseQuarter(int faculty_id, int course_id, int quarter_id);

	List<GradeCount> getGradeCountbyFacultyCourse(int faculty_id, int course_id);

	List<GradeCount> getGradeCountbyCourse(int course_id);

	List<GradeGPA> getGradeGPAFacultyCourse(int faculty_id, int course_id);

}
