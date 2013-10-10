package application.resources;

import infra.repository.BookmarkRepository;

import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Lists;

import domain.Bookmark;

/**
 * Example of REST Web Services with Spring-MVC
 */
@Controller
@RequestMapping("/service/bookmarks")
public class BookmarkResourceImpl implements BookmarkResource {

    private static final Logger logger = LoggerFactory.getLogger(BookmarkResourceImpl.class);
    
    @Inject
    private BookmarkRepository bookmarkRepository;

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Bookmark getBookmarkById(@PathVariable Long id) {
        logger.info("Call REST service #getBookmarkById");
        Bookmark bookmark = bookmarkRepository.findOne(id);
        return bookmark;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Bookmark> getAllBookmarks() {
        logger.info("Call REST service #getAllBookmarks()");
        return Lists.newArrayList(bookmarkRepository.findAll());
    }
    
    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark addBookmark(@RequestBody Bookmark bookmark) {
        logger.info("Call REST service #addBookmark()");
        if (bookmark.getId() == null) {
            bookmark.setCreationDate(new DateTime());
        }
        Bookmark newBookmark = bookmarkRepository.save(bookmark);
        return newBookmark;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark editBookmark(@RequestBody Bookmark bookmark) {
        logger.info("Call REST service #editBookmark()");
        bookmark.setModificationDate(new DateTime());
        Bookmark updatedBookmark = bookmarkRepository.save(bookmark);
        return updatedBookmark;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookmark(@PathVariable Long id) {
        logger.info("Call REST service #removeBookmark()");
        bookmarkRepository.delete(id);
    }

}
