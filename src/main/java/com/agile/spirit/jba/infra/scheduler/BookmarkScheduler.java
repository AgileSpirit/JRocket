package com.agile.spirit.jba.infra.scheduler;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.agile.spirit.jba.domain.Bookmark;
import com.agile.spirit.jba.infra.repository.BookmarkRepository;
import com.google.common.collect.Lists;

@Named
public class BookmarkScheduler {

    private static Logger logger = LoggerFactory.getLogger(BookmarkScheduler.class);
    
    @Inject
    private BookmarkRepository bookmarkRepository;
    
    @Scheduled(cron="*/5 * * * * *")
    public void countAndDisplayBookmarks() {
        List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findAll());
        if (bookmarks.isEmpty()) {
            logger.info("There is no bookmark in the database");
        } else {
            logger.info("There are " + bookmarks.size() + " bookmarks in the database");
        }
    }
    
}
