package infra.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import domain.Bookmark;

public interface BookmarkRepository extends PagingAndSortingRepository<Bookmark, Long>, BookmarkRepositoryCustom {

}
