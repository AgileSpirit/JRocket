package io.jrocket.infra.repository;

import io.jrocket.domain.Bookmark;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class is an example of advanced/customized/specific query implementation.
 */
@Named
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count(String pattern) {
        String expression = "" +
                "select b.id from Bookmark b " +
                "where b.url like :pattern " +
                "or b.title like :pattern " +
                "or b.description like :pattern " +
                "order by b.creationDate desc";
        TypedQuery<Long> query = em.createQuery(expression, Long.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList().size();
    }

    @Override
    public List<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number) {
        TypedQuery<Bookmark> query = em.createQuery("from Bookmark order by creationDate desc", Bookmark.class).setMaxResults(number);
        return query.getResultList();
    }

    @Override
    public Iterable<Bookmark> findBookmarks(int offset, int size) {
        String expression = "" +
                "select b from Bookmark b " +
                "order by b.creationDate desc";
        TypedQuery<Bookmark> query = em.createQuery(expression, Bookmark.class);
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Iterable<Bookmark> findBookmarks(String pattern, int offset, int size) {
        String expression = "" +
                "select b from Bookmark b " +
                "where b.url like :pattern " +
                "or b.title like :pattern " +
                "or b.description like :pattern " +
                "order by b.creationDate desc";
        TypedQuery<Bookmark> query = em.createQuery(expression, Bookmark.class);
        query.setParameter("pattern", "%" + pattern + "%");
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

}
