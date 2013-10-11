package domain;

import static domain.Bookmark.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import infra.repository.BookmarkRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test class to demostrate Mockito usage.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    @Before
    public void setUp() {
        Bookmark mockedBookmark = mock(Bookmark.class);
        when(mockedBookmark.getId()).thenReturn(Long.valueOf(99));
        when(mockedBookmark.getUrl()).thenReturn("http://mocked.com");
        when(mockedBookmark.getDescription()).thenReturn("Mocked bookmark");

        when(bookmarkRepository.save(Mockito.any(Bookmark.class))).thenReturn(mockedBookmark);
    }

    @Test
    public void testSave() {
        // Given
        Bookmark bookmark = create("http://original.com", "Original bookmark", "Description of my original bookmark");
        assertThat(bookmark.getId()).isEqualTo(null);

        // When
        bookmark = bookmarkService.save(bookmark);

        // Then
        verify(bookmarkRepository).save(Mockito.any(Bookmark.class));

        assertThat(bookmark.getId()).isEqualTo(Long.valueOf(99));
        assertThat(bookmark.getUrl()).isEqualTo("http://mocked.com");
    }

}
