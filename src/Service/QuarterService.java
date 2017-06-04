package Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IQuarterService;
import Model.Quarter;
import Model.QuarterName;
import Repository.QuarterRepository;

@Service
public class QuarterService implements IQuarterService{

	@Autowired
	private QuarterRepository quarterRepository;
	
	@Override
	@Transactional
	public List<Quarter> getAllQuarter() {
		return quarterRepository.getAllQuarter();
	}

	@Override
	@Transactional
	public List<QuarterName> getAllQuarterName() {
		return quarterRepository.getAllQuarterName();
	}
}
