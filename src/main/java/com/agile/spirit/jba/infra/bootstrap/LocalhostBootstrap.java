package com.agile.spirit.jba.infra.bootstrap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import com.agile.spirit.jba.domain.Bookmark;
import com.agile.spirit.jba.infra.mailing.MailService;
import com.agile.spirit.jba.infra.repository.BookmarkRepository;
import com.agile.spirit.jba.infra.util.DataGenerator;
import com.google.common.collect.Lists;

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
        dataGenerator.retrieveAndDisplayAllData();
        sendTestMail();
    }

    private void sendTestMail() {
        // Retrieve bookmarks
        final List<Bookmark> bookmarks = Lists.newArrayList(bookmarkRepository.findAll());

        // Prepare mail content data model
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("bookmarks", bookmarks);

        // Send mail
        mailService.sendMail("JavaBackbone test", mailTo, "sendBookmarks.vm", model);
    }

}
