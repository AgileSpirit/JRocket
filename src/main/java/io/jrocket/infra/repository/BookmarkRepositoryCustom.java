package io.jrocket.infra.repository;

import io.jrocket.domain.Bookmark;

public interface BookmarkRepositoryCustom {

    Iterable<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number);

}
