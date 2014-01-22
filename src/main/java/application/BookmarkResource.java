package application;

import domain.Bookmark;

import java.util.List;

public interface BookmarkResource {

  String ping();

  Bookmark getBookmarkById(Long id);

  List<Bookmark> getAllBookmarks();

  Bookmark saveBookmark(Bookmark bookmark);

  Bookmark updateBookmark(Bookmark bookmark);

  void removeBookmark(Long id);

}
