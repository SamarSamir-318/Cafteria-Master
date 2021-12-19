package eg.gov.iti.cafetria.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Medhat
 */
@Controller
public class HTTPErrorController {


    @RequestMapping(value = "/errors/404")
    public String handle404() {
        return "error/error404";
    }

    @RequestMapping(value = "/errors/403")
    public String handle403() {
        return "error/error403";
    }

    @RequestMapping(value = "/errors/500")
    public String handle500() {
        return "error/error500";
    }
}
