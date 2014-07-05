package io.jrocket.infra.repository;

import io.jrocket.domain.Bookmark;
import org.springframework.data.domain.Pageable;

public interface BookmarkRepositoryCustom {

    long count(String pattern);

    Iterable<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number);

    Iterable<Bookmark> findBookmarks(int offset, int size);

    Iterable<Bookmark> findBookmarks(String pattern, int offset, int size);
}
