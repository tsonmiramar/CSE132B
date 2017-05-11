package IRepository;

import java.util.List;

import Model.DegreeType;
import Model.UnitRequirement;

public interface IDegreeRepository {
	public void insertDegreeRequirement(UnitRequirement unitRequirement);

	List<DegreeType> getAllDegreeType();
}
