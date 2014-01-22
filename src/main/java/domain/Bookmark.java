package domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Bookmark extends AbstractPersistable<Long> {

  @Column(nullable = false, length = 1024)
  private String url;

  @Column(nullable = false, length = 1024)
  private String title;

  @Column(nullable = true, length = 1024)
  private String description;

  @Column(nullable = false)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime creationDate;

  @Column(nullable = true)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime modificationDate;

  public static Bookmark create(String url, String title, String description) {
    Bookmark bookmark = new Bookmark();
    bookmark.url = url;
    bookmark.title = title;
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
