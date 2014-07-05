package io.jrocket.infra.scheduler;

import com.google.common.collect.Lists;
import io.jrocket.domain.Bookmark;
import io.jrocket.infra.repository.BookmarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * This class is an example of scheduled task implementation.
 */
@Named
public class SchedulerExample {

    private static Logger logger = LoggerFactory.getLogger(SchedulerExample.class);

    @Inject
    private BookmarkRepository bookmarkRepository;

    /*
     * Retrieve, count and display the whole bookmarks every minute.
     */
    @Scheduled(cron = "0 */1 * * * *")
    public void countAndDisplayBookmarks() {
        List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findAll());
        if (bookmarks.isEmpty()) {
            logger.info("There is no bookmark in the database");
        } else {
            logger.info("There are " + bookmarks.size() + " bookmarks in the database");
        }
    }

}
