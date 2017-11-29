package infrastructure.event.handling.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyContextRefreshedEventListener {

    private static final String CONTEXT_HAS_BEEN_REFRESHED = "Context has been refreshed: appName=%s, contextName=%s, contextId=%s";

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        String message = String.format(CONTEXT_HAS_BEEN_REFRESHED, applicationContext.getApplicationName(),
                applicationContext.getDisplayName(),
                applicationContext.getId());
        log.info(message);
    }
}
