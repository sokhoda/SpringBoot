package web.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pizzaservice.CustomMailService;
import web.infrastructure.Routes;

import javax.inject.Inject;

@Slf4j
@Controller
public class EmailController {
    private static final String ATTACHMENT_URL = "https://i.stack.imgur.com/HGWwt.png";
    private static final String ATTACHMENT_FILENAME = "E:\\someFile.png";

    @Inject
    private CustomMailService customMailService;

    @RequestMapping(Routes.MAIL_SEND)
    public String sendMail() {
        customMailService.sendMail(ATTACHMENT_URL, ATTACHMENT_FILENAME, null);
        return Routes.DASHBOARD;
    }
}
