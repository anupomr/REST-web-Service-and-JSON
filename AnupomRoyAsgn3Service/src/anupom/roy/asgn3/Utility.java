package anupom.roy.asgn3;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import asgn1.solution.dto.Course;
import asgn1.solution.dto.Professor;
import asgn1.solution.exceptions.ImproperInputException;

public class Utility {
	public static   Course courseConverter(JsonObject jo) throws ImproperInputException {
		
		String courseCode = jo.getString("courseCode");
		String courseTitle = jo.getString("courseTitle");
		Course newCourse = new Course(courseCode, courseTitle);
		
		JsonObject joProf=jo.getJsonObject("professor");
		if(joProf!=null) {
			Professor prof=profConverter(joProf);
			newCourse.setProfessor(prof);			
		}
		return newCourse;
		
	}
	public static Professor profConverter(JsonObject jo) throws ImproperInputException {
		String firstName=jo.getString("firstName");
		String middleName=jo.getString("middleName");
		String lastName=jo.getString("lastName");
		 Professor prof = new Professor(firstName,middleName,lastName);
		 return prof;
				
	}

	public static JsonObject jsonObjectConverter(Course co) {
		JsonObjectBuilder jsonCourseBuilder = Json.createObjectBuilder();
		jsonCourseBuilder.add("courseCode", co.getCourseCode());
		jsonCourseBuilder.add("courseTitle", co.getCourseTitle());
		if(co.getProfessor()!=null) {
			jsonCourseBuilder.add("professor", jsonObjectConverter(co.getProfessor()));
		}
		JsonObject 	jsonCourseObj=jsonCourseBuilder.build();
		return jsonCourseObj;
	}
	public static JsonObject jsonObjectConverter(Professor prof) {
		JsonObject profJsonObj = Json.createObjectBuilder() 
				 .add("firstName", prof.getFirstName()) 
				 .add("middleName", prof.getMiddleName()) 
				 .add("lastName", prof.getLastName()) 
				 .build();
		return profJsonObj;
	}
}
