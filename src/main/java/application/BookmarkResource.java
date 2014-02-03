package application;

import domain.Bookmark;

import java.util.List;

public interface BookmarkResource {

    String ping();

    /**
     * Retrieve a bookmark by its <code>id</code>.
     *
     * @param id
     * @return
     */
    Bookmark getBookmarkById(Long id);

    /**
     * Retrieve all the bookmarks.
     *
     * @return
     */
    List<Bookmark> getAllBookmarks();

    /**
     * Save a new bookmark.
     *
     * @param bookmark
     * @return
     */
    Bookmark saveBookmark(Bookmark bookmark);

    /**
     * Update a bookmark.
     *
     * @param id
     * @param bookmark
     * @return
     */
    Bookmark updateBookmark(Long id, Bookmark bookmark);

    /**
     * Delete a bookmark by its id.
     *
     * @param id
     */
    void removeBookmark(Long id);

}
