package domain;

import java.util.List;

public interface BookmarkService {

    public abstract Bookmark findOne(Long id);

    public abstract Bookmark save(Bookmark bookmark);

    public abstract Bookmark update(Bookmark bookmark);

    public abstract List<Bookmark> findAll();

    public abstract void delete(Long id);

}