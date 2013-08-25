package com.agile.spirit.jba.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Bookmark extends AbstractPersistable<Long> {

    @Column
    private String url;

    @Column
    private String description;

    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;
    
    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updateDate;

    public static Bookmark create(String url, String description) {
        Bookmark bookmark = new Bookmark();
        bookmark.url = url;
        bookmark.description = description;
        bookmark.creationDate = new DateTime();
        return bookmark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = updateDate;
    }

}
