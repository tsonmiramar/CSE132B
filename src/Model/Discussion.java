package Model;

import javax.persistence.*;

@Entity
@Table(name="DISCUSSION")
@PrimaryKeyJoinColumn(name="id")
public class Discussion extends WeeklyMeeting{
		
		@Column(name="required")
		private boolean required;
		
		public Discussion() {
			super();
		}

		public boolean isRequired() {
			return required;
		}

		public void setRequired(boolean required) {
			this.required = required;
		}
		
}
