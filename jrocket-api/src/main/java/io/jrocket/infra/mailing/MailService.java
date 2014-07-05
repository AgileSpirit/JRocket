package io.jrocket.infra.mailing;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * Infrastructural service used to prepare and send emails.
 */
@Named
public class MailService {

    private static Logger logger = LoggerFactory.getLogger(MailService.class);

    @Inject
    private JavaMailSenderImpl mailSender;

    @Inject
    private VelocityEngine velocityEngine;

    /**
     * Generic method in order to send a mail.
     *
     * @param mailSubject the subject of the mail (field 'Subject:')
     * @param mailTo the recipient of the mail  (field 'To:')
     * @param templateName the name of the template to use (ex: "sendBookmarks.vm") ; the templates are expected to be
     *                     in folder src/main/java/resources/velocity
     * @param model
     */
    public void sendMail(final String mailSubject, final String mailTo, final String templateName, final Map<String, Object> model) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailTo);
                message.setSubject(mailSubject);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);
                message.setText(text, true);
            }
        };
        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {
            // Error is caught and simply logged because we do not want to make it blocking the application.
            logger.error(ex.getMessage());
        }
    }

}
