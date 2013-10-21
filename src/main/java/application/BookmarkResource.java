package application;

import java.util.List;

import domain.Bookmark;

public interface BookmarkResource {

    String ping();
    
    Bookmark getBookmarkById(Long id);
    
    List<Bookmark> getAllBookmarks();
    
    Bookmark addBookmark(Bookmark bookmark);

    Bookmark editBookmark(Bookmark bookmark);
    
    void removeBookmark(Long id);
    
}
