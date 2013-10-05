package application.resources;

import java.util.List;

import domain.Bookmark;

public interface BookmarkResource {

    Bookmark getBookmarkById(Long id);
    
    List<Bookmark> getAllBookmarks();
    
    Bookmark addBookmark(Bookmark bookmark);

    Bookmark editBookmark(Bookmark bookmark);
    
    void removeBookmark(Long id);
    
}
