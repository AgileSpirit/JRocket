package domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

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
        Assertions.assertThat(bookmark.getUrl()).isEqualTo(inputUrl);
        Assertions.assertThat(bookmark.getDescription()).isEqualTo(inputDescription);
        Assertions.assertThat(bookmark.getCreationDate()).isNotNull();
        Assertions.assertThat(bookmark.getModificationDate()).isNull();
    }

}
