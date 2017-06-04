package Model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="WEEKLY_MEETING")
@PrimaryKeyJoinColumn(name="id")
public class WeeklyMeeting extends Meeting{
	
	@Column(name="weekday")
	private String weekday;
	
	public WeeklyMeeting(){super();}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	};
	
}
