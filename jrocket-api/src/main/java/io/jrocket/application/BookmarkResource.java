package io.jrocket.application;

import io.jrocket.domain.Bookmark;

import java.util.List;

/**
 * Interface for the REST resources that deal with bookmarks.
 */
public interface BookmarkResource {

    /**
     * A simple resource in order to check the system.
     *
     * @return the <code>String</code> "pong" if the system is up and the REST resources accessible.
     */
    String ping();

    /**
     * Retrieve a bookmark by its ID.
     *
     * @param id the ID of the bookmark
     * @return the bookmark found
     */
    Bookmark getBookmarkById(Long id);

    /**
     * Retrieve all the bookmarks.
     *
     * @return the list of all the bookmarks
     */
    List<Bookmark> getAllBookmarks();

    /**
     * Save a bookmark.
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

    BookmarkSearchResponse searchBookmarks(String query, int page, int size);

}
