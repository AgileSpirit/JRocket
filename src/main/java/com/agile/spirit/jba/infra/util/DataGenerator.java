package com.agile.spirit.jba.infra.util;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agile.spirit.jba.domain.Bookmark;
import com.agile.spirit.jba.infra.repository.BookmarkRepository;
import com.google.common.collect.Lists;

@Service
public class DataGenerator {

    @Inject
    private BookmarkRepository bookmarkRepository;

    private static Logger logger = LoggerFactory.getLogger(DataGenerator.class);

    public void populateData() {
        List<Bookmark> bookmarks = Lists.newArrayList();

        bookmarks.add(Bookmark.create("http://agile-spirit.fr", "Agile Spirit"));
        bookmarks.add(Bookmark.create("http://octo.com", "OCTO Technology"));
        bookmarks.add(Bookmark.create("http://google.com", "Google"));
        bookmarks.add(Bookmark.create("http://amazon.com", "Amazon"));
        bookmarks.add(Bookmark.create("http://facebook.com", "Facebook"));
        bookmarks.add(Bookmark.create("http://twitter.com", "Twitter"));
        bookmarks.add(Bookmark.create("http://linkedin.com", "LinkedIn"));
        bookmarks.add(Bookmark.create("http://ebay.com", "Ebay"));

        bookmarkRepository.save(bookmarks);
    }

    public void retrieveAndDisplayAllData() {
        displayData(bookmarkRepository.findAll());
    }

    public void retrieveAndDisplaySortedData() {
        displayData(bookmarkRepository.findLastBookmarksOrderByCreationDateDesc(3));
    }

    private void displayData(Iterable<Bookmark> items) {
        List<Bookmark> bookmarks = Lists.newArrayList(items);
        if (bookmarks.isEmpty()) {
            logger.info("There is no data");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n\"bookmarks\" : {\r\n");
            for (Bookmark bookmark : bookmarks) {
                sb.append("  {\r\n");
                sb.append("    \"id\" : \"" + bookmark.getId() + "\",\r\n");
                sb.append("    \"url\" : \"" + bookmark.getUrl() + "\",\r\n");
                sb.append("    \"description\" : \"" + bookmark.getDescription() + "\",\r\n");
//                sb.append("    \"creationDate\" : \"" + bookmark.getCreationDate() + "\",\r\n");
//                sb.append("    \"updateDate\" : \"" + bookmark.getUpdateDate() + "\"\r\n");
                sb.append("  }\r\n");
            }
            sb.append("}");
            logger.info(sb.toString());
        }
    }

}
