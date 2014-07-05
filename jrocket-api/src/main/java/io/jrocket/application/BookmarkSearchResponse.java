package io.jrocket.application;

import io.jrocket.domain.Bookmark;

import java.util.List;

/**
 * User: OCTO-JBU
 * Date: 04/07/2014
 * Time: 19:01
 */
public class BookmarkSearchResponse {

    private String query;
    private long totalItems;
    private List<Bookmark> bookmarks;
    private int offset;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
