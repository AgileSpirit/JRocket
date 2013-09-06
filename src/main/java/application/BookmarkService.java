package application;

import domain.Bookmark;
import infra.repository.BookmarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Application service used to demostrate DDD application service and Mockito
 * usage.
 */
@Named
public class BookmarkService {

    private static Logger logger = LoggerFactory.getLogger(BookmarkService.class);

    @Inject
    private BookmarkRepository bookmarkRepository;

    BookmarkService() {
        // Empty constructor
    }

    /**
     * Parameterized constructor for Mockito with Spring DI
     *
     * @param bookmarkRepository
     */
    BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark save(Bookmark bookmark) {
        if (bookmark == null) {
            logger.error("Can not save null object.");
        }
        return bookmarkRepository.save(bookmark);
    }

}
