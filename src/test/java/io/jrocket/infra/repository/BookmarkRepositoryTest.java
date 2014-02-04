package io.jrocket.infra.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.google.common.collect.Lists;
import io.jrocket.domain.Bookmark;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseSetup(value = "classpath:dbunit/dataset/BookmarkRepositoryTest.xml")
public class BookmarkRepositoryTest extends RepositoryTest {

    @Inject
    private BookmarkRepository bookmarkRepository;

    @Test
    public void testFindAll() throws Exception {
        // Given

        // When
        List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findAll());

        // Then
        assertThat(bookmarks.size()).isEqualTo(11);
    }

    @Test
    public void testFindLastBookmarksOrderByCreationDateDesc() {
        // Given

        // When
        List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findLastBookmarksOrderByCreationDateDesc(5));

        // Then
        DateTime time = new DateTime();
        for (Bookmark bookmark : bookmarks) {
            assertThat(bookmark.getCreationDate().isBefore(time));
            time = bookmark.getCreationDate();
        }
    }

}
