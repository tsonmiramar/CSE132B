package Model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SECTION")
public class Section {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="faculty_id")
	private Faculty faculty;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="class_id")
	private CourseClass sectionClass;
	
	@Column(name="enroll_limit")
	private int enrollmentLimit;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="section", targetEntity=Meeting.class, cascade = CascadeType.ALL)
	private Collection<ReviewSession> reviewSessionList = new HashSet<ReviewSession>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="section", targetEntity=Meeting.class, cascade = CascadeType.ALL)
	private Collection<Discussion> discussionList = new HashSet<Discussion>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="section", targetEntity=Meeting.class, cascade = CascadeType.ALL)
	private Collection<NonDiscussion> nondiscussionList = new HashSet<NonDiscussion>();
	
	public Section() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public CourseClass getSectionClass() {
		return sectionClass;
	}

	public void setSectionClass(CourseClass sectionClass) {
		this.sectionClass = sectionClass;
	}

	public int getEnrollmentLimit() {
		return enrollmentLimit;
	}

	public void setEnrollmentLimit(int enrollmentLimit) {
		this.enrollmentLimit = enrollmentLimit;
	}

	public Collection<ReviewSession> getReviewSessionList() {
		return reviewSessionList;
	}

	public void setReviewSessionList(Collection<ReviewSession> reviewSessionList) {
		this.reviewSessionList = reviewSessionList;
	}

	public Collection<Discussion> getDiscussionList() {
		return discussionList;
	}

	public void setDiscussionList(Collection<Discussion> discussionList) {
		this.discussionList = discussionList;
	}

	public Collection<NonDiscussion> getNondiscussionList() {
		return nondiscussionList;
	}

	public void setNondiscussionList(Collection<NonDiscussion> nondiscussionList) {
		this.nondiscussionList = nondiscussionList;
	}

}
