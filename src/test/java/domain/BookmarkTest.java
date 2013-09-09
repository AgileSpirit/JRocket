package domain;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BookmarkTest {

    /**
     * Simple Unit Test that uses Fest Assertions 2.0 Java library.
     */
    @Test
    public void create() {
        // Given
        String inputUrl = "http://some-url.fr";
        String inputDescription = "Lorem ipsum dolor sit amet...";

        // When
        Bookmark bookmark = Bookmark.create(inputUrl, inputDescription);

        // Then
        assertThat(bookmark.getUrl()).isEqualTo(inputUrl);
        assertThat(bookmark.getDescription()).isEqualTo(inputDescription);
        assertThat(bookmark.getCreationDate()).isNotNull();
        assertThat(bookmark.getModificationDate()).isNull();
    }

}
