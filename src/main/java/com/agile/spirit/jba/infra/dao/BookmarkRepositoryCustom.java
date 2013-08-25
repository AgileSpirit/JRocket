package com.agile.spirit.jba.infra.dao;

import com.agile.spirit.jba.domain.Bookmark;

public interface BookmarkRepositoryCustom {

    Iterable<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number);
    
}
