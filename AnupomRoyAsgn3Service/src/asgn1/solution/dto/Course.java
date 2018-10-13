package asgn1.solution.dto;

import java.io.Serializable;

import asgn1.solution.exceptions.ImproperInputException;

//This class is Serializable for future use as data transfer bean
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String courseCode;
	private String courseTitle;
	private Professor professor;
	
	// default constructor for future use and JavaBean Standard
	public Course() {
		super();
	}
	
	public Course( String courseCode) throws ImproperInputException {
		super();
		setCourseCode(courseCode);
	}
	
	public Course(String courseCode, String courseTitle) throws ImproperInputException  {
		this( courseCode );
		setCourseTitle(courseTitle);
	}
	
	public Course(String code, String title, Professor professor) throws ImproperInputException   {
		this(code,title);
		this.professor = professor;
}
	public String getCourseCode() {
		return courseCode;
	}

	private void setCourseCode(String courseCode) throws ImproperInputException {
		if ( courseCode == null || courseCode.isEmpty() ) {
			throw new ImproperInputException("Course must have a course code");
		}
		courseCode.toUpperCase();
		if ( ! courseCode.matches("[A-Z]{3,4} ?[0-9]{3,4}" ) ) {
			throw new ImproperInputException(
				"Course code must be 3 or 4 letters followed by 3 or 4 digits" );
		}
		this.courseCode = courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		// assume a course can have an empty or null title
		this.courseTitle = courseTitle;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		// professor can be null when professor not assigned
		this.professor = professor;
	}

	
	public String toString() {
		String output = getCourseCode() + " [" + getCourseTitle() + "]";
		if (getProfessor() != null ) {
			output += " Professor " + getProfessor();
		}
		return output;
	}
	
    // hashCode() method generated by Eclipse IDE tool 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseCode == null) ? 0 : courseCode.hashCode());
		result = prime * result + ((courseTitle == null) ? 0 : courseTitle.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		return result;
	}

    // equals() method generated by Eclipse IDE tool 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseCode == null) {
			if (other.courseCode != null)
				return false;
		} else if (!courseCode.equals(other.courseCode))
			return false;
		if (courseTitle == null) {
			if (other.courseTitle != null)
				return false;
		} else if (!courseTitle.equals(other.courseTitle))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		return true;
	}
	



}
