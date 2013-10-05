package application.resources;

import infra.repository.BookmarkRepository;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Example of REST Web Services with Spring-MVC
 */
@Controller
@RequestMapping("/bookmark")
public class BookmarkResource {

    @Inject
    private BookmarkRepository bookmarkRepository;

}
