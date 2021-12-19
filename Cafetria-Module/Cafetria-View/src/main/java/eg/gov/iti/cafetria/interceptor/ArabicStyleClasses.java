package eg.gov.iti.cafetria.interceptor;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Project : View
 * @ClassName : ArabicStyleClasses.java
 * @Description :
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since May 30, 2017
 */
public class ArabicStyleClasses extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
       
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      
        String[] classes = {"col-sm-push-10", "col-sm-push-4", "col-sm-pull-7"};
        Locale loc = LocaleContextHolder.getLocale();
        if (loc.getLanguage().equalsIgnoreCase("ar")) {
            request.setAttribute("cssClasses", classes);
        }
    }
   
}
