package io.jrocket.domain;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import io.jrocket.infra.repository.BookmarkRepository;
import org.joda.time.DateTime;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Application service used to demostrate DDD application service and Mockito usage.
 */
@Named
public class BookmarkServiceImpl implements BookmarkService {

    @Inject
    private BookmarkRepository bookmarkRepository;

    BookmarkServiceImpl() {
        // Empty constructor
    }

    BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Timed
    public Bookmark findOne(Long id) {
        return bookmarkRepository.findOne(id);
    }

    @Timed
    public List<Bookmark> findAll() {
        final List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.DESC, "creationDate"));
        orders.add(new Order(Direction.DESC, "modificationDate"));

        return Lists.newArrayList(bookmarkRepository.findAll(new Sort(orders)));
    }

    @Override
    public List<Bookmark> find(String query, int offset, int size) {
        List<Bookmark> bookmarks = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            Iterable<Bookmark> data = bookmarkRepository.findBookmarks(offset, size);
            bookmarks = Lists.newArrayList(data);
        } else {
            Iterable<Bookmark> data = bookmarkRepository.findBookmarks(query, offset, size);
            bookmarks = Lists.newArrayList(data);
        }
        return bookmarks;
    }

    @Timed
    public Bookmark save(Bookmark bookmark) {
        bookmark.setCreationDate(new DateTime());
        return bookmarkRepository.save(bookmark);
    }

    @Timed
    public Bookmark update(Bookmark bookmark) {
        bookmark.setModificationDate(new DateTime());
        return bookmarkRepository.save(bookmark);
    }

    @Timed
    public void delete(Long id) {
        bookmarkRepository.delete(id);
    }

    @Override
    public Long count(String query) {
        if (query == null || query.trim().isEmpty()) {
            return bookmarkRepository.count();
        } else {
            return bookmarkRepository.count(query);
        }
    }

}
