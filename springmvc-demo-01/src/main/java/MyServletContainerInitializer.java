import com.xp.config.Test;
import com.xp.servlet.SpringServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * @author xupan
 * @date 2021/12/18 15:37
 **/
//@HandlesTypes(Test.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
	/*	System.out.println("****************MyServletContainerInitializer");
	//	System.out.println(set.size());
		ServletRegistration.Dynamic register = servletContext.addServlet("xx", new SpringServlet());
		register.addMapping("/a");*/
	}
}
