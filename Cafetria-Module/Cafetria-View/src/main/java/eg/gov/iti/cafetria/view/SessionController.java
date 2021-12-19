/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.service.UserService;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author atom
 */
@Controller
@SessionAttributes({"user"})
public class SessionController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, Model model) {
        if (error != null) {
            expireSession(request);
            model.addAttribute("error", "Invalid username and/or password!");
        }
        if (logout != null) {
            expireSession(request);
            return "home/home";
        }
        return "admin/login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(HttpServletRequest request) {
        org.springframework.security.core.userdetails.User authUser
                = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(authUser.getUsername());
        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (userService.isAdmin(user)) {
                return "admin/orders/showAllOrders";
            } else {
                return "home/home";
            }
        }
        return "admin/login";
    }

    private void expireSession(HttpServletRequest request) {
        SecurityContextHolder.getContext().setAuthentication(null);
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

        AnonymousAuthenticationToken aat = new AnonymousAuthenticationToken("your key you use",
                "anonymousUser", list);
        ((SecurityContext) SecurityContextHolder.getContext()).setAuthentication(aat);

//        SecurityContextHolder.clearContext();
        HttpSession hs = request.getSession();
        Enumeration e = hs.getAttributeNames();
        while (e.hasMoreElements()) {
            String attr = (String) e.nextElement();
            hs.setAttribute(attr, null);
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
            }
        }
        hs.invalidate();
    }
}
