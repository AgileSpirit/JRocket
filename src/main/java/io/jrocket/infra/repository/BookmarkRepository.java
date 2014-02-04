package io.jrocket.infra.repository;

import io.jrocket.domain.Bookmark;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookmarkRepository extends PagingAndSortingRepository<Bookmark, Long>, BookmarkRepositoryCustom {

}
