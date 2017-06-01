package IRepository;

import java.util.List;

import Model.GradeCount;
import Model.GradeGPA;

public interface IGradeReportRepository {
	
	List<GradeCount> getGradeCountbyCourseFacultyQuarter(int course_id, int faculty_id, int quarter_id);

	List<GradeCount> getGradeCountbyCourseFaculty(int faculty_id, int course_id);

	List<GradeCount> getGradeCountbyCourse(int course_id);

	List<GradeGPA> getGradeGPAFacultyCourse(int faculty_id, int course_id);
}
