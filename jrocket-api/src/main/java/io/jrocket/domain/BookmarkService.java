package io.jrocket.domain;

import java.util.List;

/**
 * Interface for services concerning bookmark.
 */
public interface BookmarkService {

    /**
     * Retrieve a bookmark by its ID.
     *
     * @param id the ID of the bookmark to find
     * @return the bookmark found, else <code>null</code>
     */
    Bookmark findOne(Long id);

    /**
     * Save a given bookmark. Notice: also set the creation date.
     *
     * @param bookmark the bookmark to save
     * @return the persisted entity
     */
    Bookmark save(Bookmark bookmark);

    /**
     * Update a given bookmark. Notice: also set the modification date (if one already exists, overwrite it).
     *
     * @param bookmark the bookmark to update
     * @return the merged bookmark
     */
    Bookmark update(Bookmark bookmark);

    /**
     * Retrieve all the bookmarks.
     *
     * @return the list of all the persisted bookmarks
     */
    List<Bookmark> findAll();

    List<Bookmark> find(String query, int offset, int size);

    /**
     * Delete a bookmark.
     *
     * @param id the ID of the bookmark to delete
     */
    void delete(Long id);

    Long count(String query);
}