/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import eg.gov.iti.cafetria.service.PrivilageService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nour
 */
@Controller
public class PrivilegeController {

    @Autowired
    PrivilageService privilageService;

    @RequestMapping(value = "/addPrivilege", method = RequestMethod.GET)
    public String showAddform(Model model) {
        model.addAttribute("privilege", new Privilege());
        model.addAttribute("errorDiv", "none");
        return "privileges/addPrivilege";
    }

    @RequestMapping(value = "/addPrivilege", method = RequestMethod.POST)
    public String addPrivilege(@ModelAttribute("privilege") @Valid Privilege privilege, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "privileges/addPrivilege";
            }
            privilageService.insert(privilege);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ("redirect:/showPrivileges");
    }

    @RequestMapping(value = "/showPrivileges", method = RequestMethod.GET)
    public String showPrivileges(Model model) {
        ArrayList<Privilege> privileges = (ArrayList<Privilege>) privilageService.findAll();
        if (privileges == null) {
            privileges = new ArrayList<>();
        }
        model.addAttribute("allprivileges", privileges);

        return "privileges/showPrivileges";
    }

    @RequestMapping(value = "/deletePrivilege", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") Integer id) {
        privilageService.delete(id);
        return new ModelAndView("redirect:/showPrivileges");
    }

    @RequestMapping(value = "/updatePrivilege", method = RequestMethod.GET)
    public String showEditform(@RequestParam("id") Integer id, Model model) {

        Privilege privilege = privilageService.findOne(id);
        if (privilege == null) {
            privilege = new Privilege();
        }
        model.addAttribute("privilege", privilege);
        model.addAttribute("errorDiv", "none");

        return "privileges/updatePrivilege";
    }

    @RequestMapping(value = "/updatePrivilege", method = RequestMethod.POST)
    public ModelAndView updatePrivilege(@ModelAttribute("privilege") @Valid Privilege privilege, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return new ModelAndView("privileges/updatePrivilege");
            }
            privilageService.update(privilege);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ModelAndView("redirect:/showPrivileges");
    }

}
