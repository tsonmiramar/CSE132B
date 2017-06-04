package Model;

public class CourseClassConflict {
	private CourseClass classCannotTake;
	private CourseClass classConflictWith;
	public CourseClassConflict() {
		super();
	}
	public CourseClass getClassCannotTake() {
		return classCannotTake;
	}
	public void setClassCannotTake(CourseClass classCannotTake) {
		this.classCannotTake = classCannotTake;
	}
	public CourseClass getClassConflictWith() {
		return classConflictWith;
	}
	public void setClassConflictWith(CourseClass classConflictWith) {
		this.classConflictWith = classConflictWith;
	}
}
