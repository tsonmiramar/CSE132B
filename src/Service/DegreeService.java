package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IDegreeService;
import Model.DegreeType;
import Model.UnitRequirement;
import Repository.DegreeRepository;

@Service
public class DegreeService implements IDegreeService {
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Override
	@Transactional
	public void insertDegreeRequirement(UnitRequirement unitRequirement) {
		degreeRepository.insertDegreeRequirement(unitRequirement);

	}

	@Override
	@Transactional
	public List<DegreeType> getAllDegreeType() {
		return degreeRepository.getAllDegreeType();
	}

}
