package infra.repository;

import domain.Bookmark;

public interface BookmarkRepositoryCustom {

    Iterable<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number);

}
