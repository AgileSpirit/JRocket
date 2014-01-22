package application;

import domain.Bookmark;
import domain.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

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
    Bookmark bookmark = bookmarkService.findOne(id);
    return bookmark;
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
  public Bookmark saveBookmark(@RequestBody Bookmark bookmark) {
    Bookmark result = bookmarkService.save(bookmark);
    return result;
  }

  @Override
  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
  @ResponseBody
  public Bookmark updateBookmark(@RequestBody Bookmark bookmark) {
    Bookmark result = bookmarkService.update(bookmark);
    return result;
  }

  @Override
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeBookmark(@PathVariable Long id) {
    bookmarkService.delete(id);
  }

}
