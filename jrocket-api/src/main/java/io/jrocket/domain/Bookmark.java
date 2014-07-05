package io.jrocket.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity class that defines a bookmark.
 */
@JsonIgnoreProperties({"new"})
@Entity
public class Bookmark extends AbstractPersistable<Long> {

    /**
     * The URL pointed by the bookmark.
     */
    @Column(nullable = false, length = 1024)
    private String url;

    /**
     * The title of the bookmark.
     */
    @Column(nullable = false, length = 1024)
    private String title;

    /**
     * The description of the bookmark.
     */
    @Column(nullable = true, length = 1024)
    private String description;

    /**
     * The creation date of the bookmark.
     */
    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    /**
     * The last modification date of the bookmark (<code>null</code> if the bookmark has never been edited).
     */
    @Column(nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationDate;

    /**
     * Factory method to instance a bookmark.
     *
     * @param url the URL of the bookmark
     * @param title the title of the bookmark
     * @param description the description of the bookmark
     * @return the created bookmark
     */
    public static Bookmark create(String url, String title, String description) {
        Bookmark bookmark = new Bookmark();
        bookmark.url = url;
        bookmark.title = title;
        bookmark.description = description;
        return bookmark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(DateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

}
