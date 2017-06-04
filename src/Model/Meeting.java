package Model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Configuration.SqlTimeDeserializer;

@Entity
@Table(name="MEETING")
@Inheritance(strategy=InheritanceType.JOINED)
public class Meeting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="start_time")
	@JsonFormat(pattern ="HH:mm")
	@JsonDeserialize(using=SqlTimeDeserializer.class)
	@Type(type="time")
	private Time startTime;
	
	@Column(name="end_time")
	@JsonFormat(pattern ="HH:mm")
	@JsonDeserialize(using=SqlTimeDeserializer.class)
	@Type(type="time")
	private Time endTime;
	
	@Column(name="room")
	private String room;
	
	@Column(name="building")
	private String building;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="section_id")
	private Section section;
	
	public Meeting(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
}
