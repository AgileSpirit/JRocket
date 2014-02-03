package infra.config;

import com.codahale.metrics.servlets.AdminServlet;
import infra.monitoring.metrics.HealthCheckServletContextListener;
import infra.monitoring.metrics.MetricsServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * <p>
 * Java based configuration equivalent to traditional 'web.xml' configuration file.
 * </p>
 * <p>
 * <!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd" >
 * <p/>
 * <web-app> <display-name>Archetype Created Web Application</display-name>
 * <p/>
 * <!-- Spring Web MVC application configuration --> <servlet> <servlet-name>app</servlet-name>
 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> <init-param>
 * <param-name>contextConfigLocation</param-name> <param-value></param-value> </init-param> <init-param>
 * <param-name>spring.profiles.default</param-name> <param-value>localhost</param-value> </init-param> <load-on-startup>1</load-on-startup>
 * </servlet> <servlet-mapping> <servlet-name>app</servlet-name> <url-pattern>/</url-pattern> </servlet-mapping>
 * <p/>
 * </web-app>
 * </p>
 */
public class WebAppInitializer implements WebApplicationInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext container) {
        addSpringWebSupport(container);
        addMetricsSupport(container);
    }

    private void addSpringWebSupport(ServletContext container) {
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(ApplicationConfig.class);
        dispatcherContext.getEnvironment().setDefaultProfiles("localhost");

        // Register and map the dispatcher servlet
        LOGGER.info("Add servlet : DispatcherServlet");
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        servlet.setLoadOnStartup(getServletIndex());
        servlet.addMapping("/*");
        
        /*
         * This listener is required for ServletListener to be aware of Spring context (ex: Metrics servlets)
         */
        container.addListener(new ContextLoaderListener(dispatcherContext));
    }

    private void addMetricsSupport(ServletContext container) {
        // Register Metrics AdminServlet
        ServletRegistration.Dynamic servlet = container.addServlet("metrics", new AdminServlet());
        servlet.setLoadOnStartup(getServletIndex());
        servlet.addMapping("/monitoring/metrics/*");

        /*
         * These listeners are required by AdminServlet, that looks in their two ContextAttributes (MetricRegistry and HealthCheckRegistry)
         */
        container.addListener(MetricsServletContextListener.class);
        container.addListener(HealthCheckServletContextListener.class);
    }

    private static int servletIndex = 0;

    private final static int getServletIndex() {
        return ++servletIndex;
    }
}
