package com.agile.spirit.jba.infra.repository;

import com.agile.spirit.jba.domain.Bookmark;

public interface BookmarkRepositoryCustom {

    Iterable<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number);
    
}
