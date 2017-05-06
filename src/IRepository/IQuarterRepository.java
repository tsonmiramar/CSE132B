package IRepository;

import java.util.List;

import Model.Quarter;
import Model.QuarterName;

public interface IQuarterRepository {
	public List<Quarter> getAllQuarter();
	
	public List<QuarterName> getAllQuarterName();
}
