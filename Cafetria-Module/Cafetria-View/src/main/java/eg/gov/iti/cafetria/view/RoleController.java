/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.converter.RoleConverter;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.dto.RoleDTO;
import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import eg.gov.iti.cafetria.service.PrivilageService;
import eg.gov.iti.cafetria.service.RoleService;
import java.util.ArrayList;
import java.util.List;
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
public class RoleController {

    @Autowired
    PrivilageService privilageService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String initform(Model model) {
        model.addAttribute("role", new RoleDTO());
        model.addAttribute("errorDiv", "none");
        return "roles/AddRole";
    }

    @ModelAttribute(value = "privilageslist")
    public List<Privilege> getPrivilages() {
        List<Privilege> privileges = (List<Privilege>) privilageService.findAll();
        return privileges;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole(@Valid @ModelAttribute("role") RoleDTO roleDto, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "roles/AddRole";
            }
            roleService.insert(roleDto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/showallroles";
    }

    @RequestMapping("/showallroles")
    public ModelAndView ShowAllRoles() {
        List<Role> roleslist = (List<Role>) roleService.findAll();
        List<RoleDTO> convertedrolelist = convertToDTO(roleslist);
        return new ModelAndView("roles/showroles", "rolelist", convertedrolelist);
    }

    @RequestMapping(value = "/showSelectedRole", method = RequestMethod.GET)
    public ModelAndView showRole(@RequestParam("id") int id, Model model) {
        RoleDTO roleDTO = roleService.findOneDTO(id);
        model.addAttribute("errorDiv", "none");
        model.addAttribute("role", roleDTO);
        return new ModelAndView("roles/updateRole");
    }

    @RequestMapping("/deleteRole")
    public String removeRole(@RequestParam("id") int id) {
        roleService.delete(id);
        return "redirect:/showallroles";
    }

    @RequestMapping(value = "/showSelectedRole", method = RequestMethod.POST)
    public String updateRole(@Valid @ModelAttribute("role") RoleDTO roleDTO, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "roles/updateRole";
            }
            roleService.update(roleDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/showallroles";
    }

    private List<RoleDTO> convertToDTO(List<Role> rolelist) {
        List<RoleDTO> result = new ArrayList<>();
        for (Role role : rolelist) {
            result.add(RoleConverter.toDTO(role));
        }
        return result;
    }

}
