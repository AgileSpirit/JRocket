package infra.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * <p>Java based configuration equivalent to traditional 'web.xml' configuration file.</p>
 * <p>
 * <!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd" >
 * 
 * <web-app>
 *   <display-name>Archetype Created Web Application</display-name>
 * 
 *   <!-- Spring Web application configuration --> 
 *   <context-param>
 *     <param-name>contextClass</param-name>
 *     <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
 *   </context-param>
 *   <context-param>
 *     <param-name>contextConfigLocation</param-name>
 *     <param-value>infra.config.ApplicationConfig</param-value>
 *   </context-param>
 *   <context-param>
 *     <param-name>spring.profiles.default</param-name>
 *     <param-value>localhost</param-value>
 *   </context-param>
 *   <listener>
 *     <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 *   </listener>
 * 
 *   <!-- Spring Web MVC / REST support -->
 *   <servlet>
 *     <servlet-name>app</servlet-name>
 *     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 *     <init-param>
 *       <param-name>contextConfigLocation</param-name>
 *       <param-value></param-value>
 *     </init-param>
 *     <load-on-startup>1</load-on-startup>
 *   </servlet>
 *   <servlet-mapping>
 *     <servlet-name>app</servlet-name>
 *     <url-pattern>/</url-pattern>
 *   </servlet-mapping>
 * 
 * </web-app>
 * </p>
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class);
        rootContext.getEnvironment().setDefaultProfiles("localhost");

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
