package anupom.roy.asgn3;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/")
public class ServiceConfig extends Application {
    
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
// register (add) the class that performs your service - change the name 
		classes.add( CourseWebService.class );
// register (add) the class that maps CourseNotFOundException to HTTP return code 
		classes.add(ResultCourseNotFound.class);
// register (add) other classes that use the JAX-RS annotations or API
//          including mappers for other exceptions
		return classes;
	}
    
}
