package Model;

import javax.persistence.*;

@Entity
@Table(name="REVIEW_SESSION")
@PrimaryKeyJoinColumn(name="id")
public class ReviewSession extends Meeting{
	
	@Column(name="month")
	private String month;
	
	@Column(name="day")
	private int day;
	
	@Column(name="weekday")
	private String weekday;
	
	public ReviewSession(){super();}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	};
}
