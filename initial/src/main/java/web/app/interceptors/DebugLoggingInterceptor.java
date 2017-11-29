package web.app.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DebugLoggingInterceptor implements HandlerInterceptor {
    private static final String INTERCEPTOR_PREHANDLE_MESSAGE = "preHandle %s. Method %s %s";
    private static final String INTERCEPTOR_POSTHANDLE_MESSAGE = "postHandle %s. Execution time %d ms";
    private static final String INTERCEPTOR_AFTERCOMPLETION_MESSAGE = "Request handling of %s %s completed";
    private long start;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String message = String.format(INTERCEPTOR_PREHANDLE_MESSAGE, getClass().getSimpleName(), request.getMethod(), request.getPathInfo());
        log.info(message);
        start = System.nanoTime();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long executionTimeNano = System.nanoTime() - start;
        long executionTimeMilli = TimeUnit.MILLISECONDS.convert(executionTimeNano, TimeUnit.NANOSECONDS);
        String message = String.format(INTERCEPTOR_POSTHANDLE_MESSAGE, getClass().getSimpleName(), executionTimeMilli);
        log.info(message);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String message = String.format(INTERCEPTOR_AFTERCOMPLETION_MESSAGE, request.getMethod(), request.getPathInfo());
        log.info(message);
    }
}
