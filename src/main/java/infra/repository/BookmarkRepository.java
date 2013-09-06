package infra.repository;

import domain.Bookmark;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark, Long>, BookmarkRepositoryCustom {

}
