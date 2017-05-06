package IService;

import java.util.List;

import Model.Quarter;
import Model.QuarterName;

public interface IQuarterService {
	public List<Quarter> getAllQuarter();
	
	public List<QuarterName> getAllQuarterName();
}
