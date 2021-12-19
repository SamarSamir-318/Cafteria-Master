package eg.gov.iti.cafetria.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project     : C-V
 * @ClassName   : TestController.java
 * @Description : 
 * @author MotYim <mohamed.motyim@gmail.com>
 * @since Jun 7, 2017 
 */
@Controller
public class TestController {

    @RequestMapping("test.htmz")
    public String me(){
        return "test/test";
    }
    
     @RequestMapping("test2.htmz")
    public String me2(){
        return "test/order";
    }
    
    @RequestMapping("test3.htmz")
    public String me3(){
        return "test/view";
    }
    @RequestMapping("test4.htmz")
    public String me4(){
        return "test/horder";
    }
    @RequestMapping("test5.htmz")
    public String me5(){
        return "test/buy";
    }
    
    @RequestMapping("adminHome.htm")
    public String me6(){
        return "admin/home";
    }
}
