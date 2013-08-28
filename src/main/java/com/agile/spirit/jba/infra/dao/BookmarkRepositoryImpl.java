package com.agile.spirit.jba.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.agile.spirit.jba.domain.Bookmark;

@Repository
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number) {
        TypedQuery<Bookmark> query = em.createQuery("from Bookmark order by creationDate desc", Bookmark.class).setMaxResults(number);
        return query.getResultList();
    }
   
}
