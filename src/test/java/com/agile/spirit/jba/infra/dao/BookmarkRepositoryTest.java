package com.agile.spirit.jba.infra.dao;

import static org.fest.assertions.api.Assertions.assertThat;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agile.spirit.jba.domain.Bookmark;
import com.agile.spirit.jba.infra.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@ActiveProfiles("test")
public class BookmarkRepositoryTest {

    @Inject
    private BookmarkRepository bookmarkRepository;

    @Test
    public void testFindLastBookmarks() {
        // Given

        // When
        Iterable<Bookmark> bookmarks = bookmarkRepository.findLastBookmarksOrderByCreationDateDesc(5);

        // Then
        DateTime time = new DateTime();
        for (Bookmark bookmark : bookmarks) {
            assertThat(bookmark.getCreationDate().isBefore(time));
            time = bookmark.getCreationDate();
        }
    }

}
