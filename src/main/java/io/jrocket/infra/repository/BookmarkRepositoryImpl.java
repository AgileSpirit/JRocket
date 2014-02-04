package io.jrocket.infra.repository;

import io.jrocket.domain.Bookmark;

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
    public List<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number) {
        TypedQuery<Bookmark> query = em.createQuery("from Bookmark order by creationDate desc", Bookmark.class).setMaxResults(number);
        return query.getResultList();
    }

}
