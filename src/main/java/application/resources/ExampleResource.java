package application.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Example of REST Web Services with Spring-MVC
 */
@Controller
@RequestMapping("/example")
public class ExampleResource {

    /**
     * Basic resource method to check that the REST mechanism is OK.
     * <p>
     * <strong>URL:</strong> http://[hostname]:[port]/[context]/example/test
     * </p>
     * 
     * @return the <code>String</code> "It works !"
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "It works !";
    }

    /**
     * Simple resource method to show how to use view/control mechanism.
     * <p>
     * <strong>URL:</strong> http://[hostname]:[port]/[context]/example/hello
     * </p>
     * 
     * @return a redirection to the JSP page WEB-INF/jsp/hello-world.jsp
     */
    @RequestMapping(value = "/hello")
    public ModelAndView goToHelloWorld() {
        return new ModelAndView("hello-world");
    }

}
