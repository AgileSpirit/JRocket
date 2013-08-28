package com.agile.spirit.jba.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agile.spirit.jba.domain.Bookmark;
import com.agile.spirit.jba.infra.repository.BookmarkRepository;

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
