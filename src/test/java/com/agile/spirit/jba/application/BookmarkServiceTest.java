package com.agile.spirit.jba.application;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.agile.spirit.jba.domain.Bookmark;
import com.agile.spirit.jba.infra.repository.BookmarkRepository;

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
        Bookmark mockedBookmark = Mockito.mock(Bookmark.class);
        Mockito.when(mockedBookmark.getId()).thenReturn(Long.valueOf(99));
        Mockito.when(mockedBookmark.getUrl()).thenReturn("http://mocked.com");
        Mockito.when(mockedBookmark.getDescription()).thenReturn("Mocked bookmark");

        Mockito.when(bookmarkRepository.save(Mockito.any(Bookmark.class))).thenReturn(mockedBookmark);        
    }
    
    @Test
    public void testSave() {
        // Given
        Bookmark bookmark = Bookmark.create("http://original.com", "Original bookmark");
        Assertions.assertThat(bookmark.getId()).isEqualTo(null);
        
        // When
        bookmark = bookmarkService.save(bookmark);
        
        // Then
        Mockito.verify(bookmarkRepository).save(Mockito.any(Bookmark.class));

        Assertions.assertThat(bookmark.getId()).isEqualTo(Long.valueOf(99));
        Assertions.assertThat(bookmark.getUrl()).isEqualTo("http://mocked.com");
    }
    
}
