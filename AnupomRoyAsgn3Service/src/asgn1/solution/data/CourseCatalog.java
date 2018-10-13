package asgn1.solution.data;

import asgn1.solution.dto.Course;
import asgn1.solution.exceptions.CourseNotFoundException;
import asgn1.solution.exceptions.DuplicateCourseException;

public interface CourseCatalog {
	
	public Course getCourse(String courseCode) throws CourseNotFoundException;

	public Course addCourse(Course c) throws DuplicateCourseException;

	public Course deleteCourse(String courseCode) throws CourseNotFoundException;
	
	public Course updateCourse(Course c) throws CourseNotFoundException;
}
