package Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import IService.IMeetingService;
import Model.Weekday;
import Repository.WeekdayRepository;

@Service
public class MeetingService implements IMeetingService {

	@Autowired
	private WeekdayRepository weekdayRepository;
	
	@Override
	@Transactional
	public List<Weekday> getAllWeekday() {
		return weekdayRepository.getAllWeekDay();
	}

}
