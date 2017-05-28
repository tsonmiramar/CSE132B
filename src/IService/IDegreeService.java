package IService;

import java.util.List;

import Model.Degree;
import Model.DegreeType;
import Model.UnitRequirement;

public interface IDegreeService {
	public void insertDegreeRequirement(UnitRequirement unitRequirement);

	List<DegreeType> getAllDegreeType();

	List<Degree> getAllDegree();
}
