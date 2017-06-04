package Model;
import javax.persistence.*;

@Entity
@Table(name="NON_DISCUSSION")
@PrimaryKeyJoinColumn(name="id")
public class NonDiscussion extends WeeklyMeeting{
	
	@Column(name="type")
	private String type;

	public NonDiscussion() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
