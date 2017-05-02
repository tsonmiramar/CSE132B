package Model;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="QUARTER")
public class Quarter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="year")
	private int year;

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="quarterList")
	private Collection<CourseClass> classList = new HashSet<CourseClass>();
	
	public Quarter() {
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Collection<CourseClass> getClassList() {
		return classList;
	}

	public void setClassList(Collection<CourseClass> classList) {
		this.classList = classList;
	}
	
}
