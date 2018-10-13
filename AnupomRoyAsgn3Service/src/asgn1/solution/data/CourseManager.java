package asgn1.solution.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import asgn1.solution.dto.Course;
import asgn1.solution.dto.Professor;
import asgn1.solution.exceptions.CourseNotFoundException;
import asgn1.solution.exceptions.DuplicateCourseException;


public class CourseManager implements CourseCatalog {
	private static CourseManager instance = null;
	private Map<String, Course> courses = null;
	
	private CourseManager() { 
		courses = new ConcurrentHashMap<String, Course>();
		buildCatalog();
	}

	public static synchronized CourseManager getInstance() {
		if (instance == null) {
			instance = new CourseManager();
		}
		return instance;
	}
	
	public Collection<Course> getAllCourses() {
		return courses.values();
	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		if ( courseCode == null ) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			return courses.get(courseCode);
		} else {
			throw new CourseNotFoundException("No course with code " + courseCode);
		}
	}

	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		if ( c == null ) {
			throw new DuplicateCourseException("Cannot add a null Course");
		}
		String code = c.getCourseCode();
		if (courses.containsKey(code) ){
			throw new DuplicateCourseException("Duplicate course code " + code );
		}
		courses.put(code, c);
		return c;
	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		if ( c == null ) {
			throw new CourseNotFoundException("Cannot update a null Course");
		}
		Course oldC = getCourse(c.getCourseCode());
		if ( c.equals(oldC)) {
			// no change - nothing to do
			return c;
		}
		// insert changed course
		courses.put(c.getCourseCode(), c);
		// retrieve again to get derived fields, if there are any
		return getCourse(c.getCourseCode());
	}

	@Override
	public Course deleteCourse(String courseCode) throws CourseNotFoundException {
		if ( courseCode == null ) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			Course c = courses.get(courseCode);
			courses.remove(courseCode);
			return c;
		} else {
			throw new CourseNotFoundException("No course with code " + courseCode);
		}
	}
	
	private void buildCatalog() {
		System.out.println("Building course catalog");
		try {
			// replace up to catch to add courses you are taking this term
			courses.put("COMP231", new Course("COMP231", "Software Development Project"));
			Professor professor = new Professor("Paula", "McMillan");
			courses.put("COMP303",new Course("COMP303", "Java EE Programming", professor));
			courses.put("COMP304",new Course("COMP304", "Wireless Programming"));
			courses.put("COMP306",new Course("COMP306", "Web Service Programming"));
			courses.put("COMP307",new Course("COMP307", "Software Security"));
			courses.put("COMP309",new Course("COMP309", "Data Warehousing and Data Mining"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}

	}
}


