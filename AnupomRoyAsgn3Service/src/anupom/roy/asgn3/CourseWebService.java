package anupom.roy.asgn3;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import asgn1.solution.data.CourseManager;
import asgn1.solution.dto.Course;
import asgn1.solution.exceptions.CourseNotFoundException;
import asgn1.solution.exceptions.DuplicateCourseException;
import asgn1.solution.exceptions.ImproperInputException;

@Path("web")
@ApplicationScoped
public class CourseWebService {	

	@GET
	@Path("courses")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject getCourse(@Context UriInfo uriInfo) throws CourseNotFoundException, ImproperInputException {
		String coursCode = uriInfo.getQueryParameters().getFirst("courseCode");
		Course getcourse = CourseManager.getInstance().getCourse(coursCode);
		JsonObject model = Utility.jsonObjectConverter(getcourse);
		return model;		
	}	

	@GET
	@Path("courseAll")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject getAllCourse() {
 		Collection<Course> getcourse = null;
		getcourse = CourseManager.getInstance().getAllCourses();
		JsonArrayBuilder ab = Json.createArrayBuilder();
		for (Course next : getcourse) {
			ab.add(Utility.jsonObjectConverter(next));
		}
		JsonObject courseJasonObj = Json.createObjectBuilder().add("Course List ", ab.build()).build();
		return courseJasonObj;
	}

	@PUT
	@Path("add/{code}")
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonObject addCourse(JsonObject jsonObj) throws ImproperInputException, DuplicateCourseException {		
		Course newCourse = Utility.courseConverter(jsonObj);		
		Course addedCourse = CourseManager.getInstance().addCourse(newCourse);	
		JsonObject addedJson = Utility.jsonObjectConverter(addedCourse);
		return addedJson;
	}

	@DELETE
	@Path("remove")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonObject deleteCourse(@Context UriInfo uriInfo) throws CourseNotFoundException {
		String coursCode = uriInfo.getQueryParameters().getFirst("courseCode");
		Course getcourse = CourseManager.getInstance().deleteCourse(coursCode);
		JsonObject model = Utility.jsonObjectConverter(getcourse);
		return model;
	}

	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonObject updateCourse(JsonObject jsonObj) throws ImproperInputException, CourseNotFoundException {
		Course newCourse = Utility.courseConverter(jsonObj);
		Course updatedCourse = CourseManager.getInstance().updateCourse(newCourse);
		JsonObject model = Utility.jsonObjectConverter(updatedCourse);
		return model;
	}

}
