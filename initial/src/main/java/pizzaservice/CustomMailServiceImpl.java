package pizzaservice;

import businessdomain.Orders;
import infrastructure.email.templates.CustomOrderTemplate;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Objects;

@Service
public class CustomMailServiceImpl implements CustomMailService {
    private static final String DEFAULT_MESSAGE_BODY = "HI!!";
    private static final String RECEIVER_EMAIL = "receiver@gmail.com";
    private static final String DEFAULT_SUBJECT = "Some mail from me! ))";
    private String receiverEmail = RECEIVER_EMAIL;

    @Inject
    private JavaMailSender javaMailSender;
    @Inject
    private VelocityEngineFactoryBean velocityEngineFactoryBean;

    @Override
    public void sendMail(File file, String receiverEmail) {
        this.receiverEmail = receiverEmail;
        doSendMail(file, file.getName(), null);
    }

    @Override
    public void sendMail(File file, String attachmentFilename, Orders order) {
        doSendMail(file, attachmentFilename, order);
    }

    @Override
    public void sendMail(String attachmentUrl, String attachmentFilename, Orders order) {
        File file = loadFileByURL(attachmentUrl, attachmentFilename);
        doSendMail(file, attachmentFilename, order);
    }

    private void doSendMail(File file, String attachmentFilename, Orders order) {
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            MimeMailMessage message = new MimeMailMessage(mimeMessageHelper);
            message.setTo(receiverEmail);
            message.setSubject(DEFAULT_SUBJECT);
            populateMessageBody(order, message);
            populateAttachment(file, attachmentFilename, message);

            javaMailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            return;
        }
    }

    private void populateAttachment(File file, String attachmentFilename, MimeMailMessage message) throws MessagingException {
        if (Objects.nonNull(file)) {
            message.getMimeMessageHelper().addAttachment(attachmentFilename, file);
        }
    }

    private void populateMessageBody(Orders order, MimeMailMessage message) throws MessagingException {
        String templateBody = resolveVelocityTemplate(order);
        String messageBody = StringUtils.isEmpty(templateBody) ? DEFAULT_MESSAGE_BODY : templateBody;
        message.setText(messageBody);
    }

    private String resolveVelocityTemplate(Orders order) {
        if (order == null) {
            return StringUtils.EMPTY;
        }
        VelocityEngine ve = velocityEngineFactoryBean.getObject();
        ve.init();
        Template template = ve.getTemplate(CustomOrderTemplate.PATH);
        VelocityContext context = new VelocityContext();

        context.put(CustomOrderTemplate.CUSTOMER_FIELD, order.getCustomer());
        context.put(CustomOrderTemplate.PIZZACOUNT_FIELD, order.countPizzasQuantity());
        context.put(CustomOrderTemplate.TOTAL_PRICE_FIELD, order.calculateTotalprice());

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }


    private File loadLocalFile(String pathToAttachment) {
        return new File(pathToAttachment);
    }

    private File loadFileByURL(String fileUrl, String filename) {
        File file = new File(filename);
        try {
            URL url = new URL(fileUrl);
            FileUtils.copyURLToFile(url, file);
            return file;
        } catch (IOException e) {
            return null;
        }
    }
}
