package application;

import domain.Bookmark;
import domain.BookmarkService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkResourceImplTest {

    private static final Long SOME_ID = Long.valueOf("1");
    private static final String SOME_URL = "http://some.url";
    private static final String SOME_TITLE = "Some title";
    private static final String SOME_DESCRIPTION = "Some description";
    private static final DateTime SOME_DATE = new DateTime();
    @Mock
    private Bookmark bookmark;
    @Mock
    private BookmarkService service;
    @InjectMocks
    private BookmarkResourceImpl resource;

    @Before
    public void setUp() {
        Mockito.when(bookmark.getUrl()).thenReturn(SOME_URL);
        Mockito.when(bookmark.getTitle()).thenReturn(SOME_TITLE);
        Mockito.when(bookmark.getDescription()).thenReturn(SOME_DESCRIPTION);
    }

    /*
     * BookmarkResourceImpl#ping
     */

    public void pingShouldReturnPong() {
        // Empty test because there is no logic
        assertThat(resource.ping()).isEqualTo("pong");
    }

    /*
     * BookmarkResourceImpl#getBookmarkById
     */

    @Test
    public void getBookmarkByIdShouldBeOk() {
        resource.getBookmarkById(SOME_ID);
    }

    /*
     * BookmarkResourceImpl#getAllBookmarks
     */

    @Test
    public void getAllBookmarksShouldBeOk() {
        // Empty test because there is no logic
        resource.getAllBookmarks();
    }

    /*
     * BookmarkResourceImpl#removeBookmark
     */

    @Test
    public void removeBookmarkShouldBeOk() {
        resource.removeBookmark(SOME_ID);
    }

    /*
     * BookmarkResourceImpl#saveBookmark
     */

    @Test
    public void savingAValidBookmarkShouldBeOk() {
        // When
        resource.saveBookmark(bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void savingANullBookmarkShouldThrowAnException() {
        // When
        resource.saveBookmark(null);
    }

    @Test(expected = BadRequestParametersException.class)
    public void savingABookmarkWithMissingUrlShouldThrowAnException() {
        // Given
        Mockito.when(bookmark.getUrl()).thenReturn(null);
        // When
        resource.saveBookmark(bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void savingABookmarkWithMissingTitleShouldThrowAnException() {
        // Given
        Mockito.when(bookmark.getTitle()).thenReturn(null);
        // When
        resource.saveBookmark(bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void savingABookmarkWithIdSetShouldThrowAnException() {
        // Given
        Mockito.when(bookmark.getId()).thenReturn(SOME_ID);
        // When
        resource.saveBookmark(bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void savingABookmarkWithCreationDateSetShouldThrowAnException() {
        // Given
        Mockito.when(bookmark.getCreationDate()).thenReturn(new DateTime());
        // When
        resource.saveBookmark(bookmark);
    }

    /*
     * BookmarkResourceImpl#updateBookmark
     */

    private void configureMocksForUpdate() {
        Mockito.when(bookmark.getId()).thenReturn(SOME_ID);
        Mockito.when(bookmark.getCreationDate()).thenReturn(SOME_DATE);
        Mockito.when(service.findOne(SOME_ID)).thenReturn(bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingANullBookmarkShouldThrowAnException() {
        // When
        resource.updateBookmark(SOME_ID, null);
    }

    @Test
    public void updatingAValidBookmarkShouldBeOk() {
        // Given
        configureMocksForUpdate();
        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingABookmarkWithMissingUrlShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        Mockito.when(bookmark.getUrl()).thenReturn(null);
        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingABookmarkWithMissingTitleShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        Mockito.when(bookmark.getTitle()).thenReturn(null);
        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingABookmarkWithMissingIdShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        Mockito.when(bookmark.getId()).thenReturn(null);
        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingABookmarkWithGivenIdNullShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        // When
        resource.updateBookmark(null, bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingABookmarkWithIdDifferentFromQueryIdShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        Mockito.when(bookmark.getId()).thenReturn(Long.valueOf("2"));
        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

    @Test(expected = BadRequestParametersException.class)
    public void updatingABookmarkWithMissingCreationDateSetShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        Mockito.when(bookmark.getCreationDate()).thenReturn(null);
        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

    @Test(expected = BookmarkNotFoundException.class)
    public void updatingANonExistingBookmarkShouldThrowAnException() {
        // Given
        configureMocksForUpdate();
        Mockito.when(service.findOne(SOME_ID)).thenReturn(null);

        // When
        resource.updateBookmark(SOME_ID, bookmark);
    }

}
