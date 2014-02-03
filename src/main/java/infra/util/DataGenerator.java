package infra.util;

import com.google.common.collect.Lists;
import domain.Bookmark;
import infra.repository.BookmarkRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class DataGenerator {

    @Inject
    private BookmarkRepository bookmarkRepository;

    private static Logger logger = LoggerFactory.getLogger(DataGenerator.class);

    public void populateData() {
        List<Bookmark> bookmarks = Lists.newArrayList();

        bookmarks.add(newBookmark("http://agile-spirit.fr", "Agile Spirit", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "));
        bookmarks.add(newBookmark("http://octo.com", "OCTO Technology", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."));
        bookmarks.add(newBookmark("http://google.com", "Google Search Engine", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."));
        bookmarks.add(newBookmark("http://amazon.com", "Amazon e-commerce", "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        bookmarks.add(newBookmark("http://facebook.com", "Facebook - Social network", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo."));
        bookmarks.add(newBookmark("http://twitter.com", "Twitter - Social microblogging platform", "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt."));
        bookmarks.add(newBookmark("http://linkedin.com", "LinkedIn", "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem."));
        bookmarks.add(newBookmark("http://ebay.com", "Ebay", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga."));

        bookmarkRepository.save(bookmarks);
    }

    private Bookmark newBookmark(String url, String title, String description) {
        Bookmark bookmark = Bookmark.create(url, title, description);
        bookmark.setCreationDate(new DateTime());
        return bookmark;
    }

    public void retrieveAndDisplayAllData() {
        displayData(bookmarkRepository.findAll());
    }

    public void retrieveAndDisplaySortedData() {
        displayData(bookmarkRepository.findLastBookmarksOrderByCreationDateDesc(3));
    }

    private void displayData(Iterable<Bookmark> items) {
        List<Bookmark> bookmarks = Lists.newArrayList(items);
        if (bookmarks.isEmpty()) {
            logger.info("There is no data");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n\"bookmarks\" : {\r\n");
            for (Bookmark bookmark : bookmarks) {
                sb.append("  {\r\n");
                sb.append("    \"id\" : \"" + bookmark.getId() + "\",\r\n");
                sb.append("    \"url\" : \"" + bookmark.getUrl() + "\",\r\n");
                sb.append("    \"title\" : \"" + bookmark.getTitle() + "\",\r\n");
                sb.append("    \"description\" : \"" + bookmark.getDescription() + "\",\r\n");
                sb.append("    \"creationDate\" : \"" + bookmark.getCreationDate() + "\",\r\n");
                sb.append("    \"modificationDate\" : \"" + bookmark.getModificationDate() + "\"\r\n");
                sb.append("  }\r\n");
            }
            sb.append("}");
            logger.info(sb.toString());
        }
    }

}
