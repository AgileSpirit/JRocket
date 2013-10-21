package application;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import domain.Bookmark;
import domain.BookmarkService;

/**
 * Example of REST Web Services with Spring-MVC
 */
@Controller
@RequestMapping("/service/bookmarks")
public class BookmarkResourceImpl implements BookmarkResource {

    @Inject
    private BookmarkService bookmarkService;

    @Override
    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
    
    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Bookmark getBookmarkById(@PathVariable Long id) {
        return bookmarkService.findOne(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Bookmark> getAllBookmarks() {
        return bookmarkService.findAll();
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark addBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkService.save(bookmark);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark editBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkService.save(bookmark);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookmark(@PathVariable Long id) {
        bookmarkService.delete(id);
    }

}
