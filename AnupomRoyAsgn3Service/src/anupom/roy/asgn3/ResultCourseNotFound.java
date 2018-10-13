package anupom.roy.asgn3;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import asgn1.solution.exceptions.CourseNotFoundException;

public class ResultCourseNotFound implements ExceptionMapper<CourseNotFoundException> {

	@Override
	public Response toResponse(CourseNotFoundException exception) {
        Response r = Response.serverError()
                .status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
        return r;
	}
	
}
