package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IGradeReportService;
import Model.GradeCount;
import Model.GradeGPA;
import Repository.GradeReportRepository;

@Service
public class GradeReportService implements IGradeReportService {

	@Autowired
	private GradeReportRepository gradeReportRepository;
	
	@Override
	@Transactional
	public List<GradeCount> getGradeCountbyFacultyCourseQuarter(int faculty_id, int course_id, int quarter_id){
		return gradeReportRepository.getGradeCountbyCourseFacultyQuarter(course_id, faculty_id, quarter_id);
	}

	@Override
	@Transactional
	public List<GradeCount> getGradeCountbyFacultyCourse(int faculty_id, int course_id) {
		return gradeReportRepository.getGradeCountbyCourseFaculty(faculty_id, course_id);
	}
	
	@Override
	@Transactional
	public List<GradeCount> getGradeCountbyCourse(int course_id) {
		return gradeReportRepository.getGradeCountbyCourse(course_id);
	}

	@Override
	@Transactional
	public List<GradeGPA> getGradeGPAFacultyCourse(int faculty_id, int course_id) {
		return gradeReportRepository.getGradeGPAFacultyCourse(faculty_id, course_id);
	}
}
