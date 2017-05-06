package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="COLLEGE")
public class College {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="college")
	private Collection<UnderGradStudent> undergradList = new HashSet<UnderGradStudent>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="collegeBSMS")
	private Collection<BSMSStudent> bsmsList = new HashSet<BSMSStudent>();
	
	public College() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<UnderGradStudent> getUndergradList() {
		return undergradList;
	}

	public void setUndergradList(Collection<UnderGradStudent> undergradList) {
		this.undergradList = undergradList;
	}

	public Collection<BSMSStudent> getBsmsList() {
		return bsmsList;
	}

	public void setBsmsList(Collection<BSMSStudent> bsmsList) {
		this.bsmsList = bsmsList;
	}
	
}
