package infra.repository;

import domain.Bookmark;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookmarkRepository extends PagingAndSortingRepository<Bookmark, Long>, BookmarkRepositoryCustom {

}
