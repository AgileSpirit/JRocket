package io.jrocket.infra.bootstrap;

import com.google.common.collect.Lists;
import io.jrocket.domain.Bookmark;
import io.jrocket.infra.mailing.MailService;
import io.jrocket.infra.repository.BookmarkRepository;
import io.jrocket.infra.util.DataGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Profile("localhost")
public class LocalhostBootstrap extends ApplicationBootstrap {

    @Inject
    private DataGenerator dataGenerator;
    @Inject
    private MailService mailService;
    @Inject
    private BookmarkRepository bookmarkRepository;
    @Value("${mail.message.to}")
    private String mailTo;

    @Override
    void bootstrap() {
        dataGenerator.populateData();

        /* Uncomment the instruction below to display the populated bookmarks */
        // dataGenerator.retrieveAndDisplayAllData();

        /* Uncomment the instruction below to test mail sending after io.jrocket.application bootstraping. */
        // sendTestMail();
    }

    private void sendTestMail() {
        // Retrieve bookmarks
        final List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findAll());

        // Prepare mail content data model
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("bookmarks", bookmarks);

        // Send mail
        mailService.sendMail("JRocket test", mailTo, "sendBookmarks.vm", model);
    }

}
