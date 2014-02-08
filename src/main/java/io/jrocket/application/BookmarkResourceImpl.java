package io.jrocket.application;

import io.jrocket.domain.Bookmark;
import io.jrocket.domain.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Example of REST Web Services with Spring-MVC
 */
@Controller
@RequestMapping("/api/bookmarks")
public class BookmarkResourceImpl implements BookmarkResource {

    @Inject
    BookmarkService bookmarkService;

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
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookmark(@PathVariable Long id) {
        bookmarkService.delete(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark saveBookmark(@RequestBody Bookmark bookmark) {
        checkParametersForCreateElseThrowsException(bookmark);
        return bookmarkService.save(bookmark);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark updateBookmark(@PathVariable Long id, @RequestBody Bookmark bookmark) {
        checkParametersForUpdateElseThrowsException(id, bookmark);

        Bookmark old = bookmarkService.findOne(id);
        if (old == null) {
            throw new BookmarkNotFoundException();
        }

        return bookmarkService.update(bookmark);
    }

    private void checkBookmarkForCreateOrUpdateElseThrowsException(Bookmark bookmark) {
        if (bookmark == null) {
            throw new BadRequestParametersException("Given bookmark must not be null");
        }
        if (bookmark.getUrl() == null) {
            throw new BadRequestParametersException("Given bookmark's URL must not be null");
        }
        if (bookmark.getTitle() == null) {
            throw new BadRequestParametersException("Given bookmark's title must not be null");
        }
    }

    private void checkParametersForCreateElseThrowsException(Bookmark bookmark) {
        checkBookmarkForCreateOrUpdateElseThrowsException(bookmark);

        if (bookmark.getId() != null) {
            throw new BadRequestParametersException("Given bookmark's ID must be null");
        }
        if (bookmark.getCreationDate() != null) {
            throw new BadRequestParametersException("Given bookmark's creation date must be null");
        }
    }

    private final void checkParametersForUpdateElseThrowsException(Long id, Bookmark bookmark) {
        checkBookmarkForCreateOrUpdateElseThrowsException(bookmark);

        if (id == null) {
            throw new BadRequestParametersException("Given ID must not be null");
        }
        if (bookmark.getId() == null) {
            throw new BadRequestParametersException("Given bookmark's ID must not be null");
        }
        if (id != bookmark.getId()) {
            throw new BadRequestParametersException("Given ID and bookmark.id must be equal");
        }
        if (bookmark.getCreationDate() == null) {
            throw new BadRequestParametersException("Given bookmark's creation date must be null");
        }
    }

}
