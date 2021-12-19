/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.model.dal.domain.Worker;
import eg.gov.iti.cafetria.service.WorkerService;
import eg.gov.iti.cafetria.dto.WorkerDTO;
import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.service.CafetriaService;
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
public class WorkerController {

    @Autowired
    WorkerService workerService;
    @Autowired
    CafetriaService cafetriaService;
    @Autowired
    RoleService roleService;
    
    
//    @RequestMapping(value = "/addWorker", method = RequestMethod.GET)
    public String initform(Model model) {
        model.addAttribute("worker", new WorkerDTO());
        model.addAttribute("errorDiv", "none");
        return "workers/AddWorker";
    }

    @ModelAttribute(value = "roleslist")
    public List<Role> getRoles(){
        List<Role> roles = (List<Role>) roleService.findAll();
		if(roles == null)
			roles = new ArrayList<>();
        return roles;
    }
    
    @ModelAttribute(value = "cafetriaslist")
    public List<Cafetria> getCafetrias(){
        List<Cafetria> cafetrias=(List<Cafetria>) cafetriaService.findAll();
		if(cafetrias == null)
			cafetrias = new ArrayList<>();
        return cafetrias;
    }
//    @RequestMapping(value = "/addWorker", method = RequestMethod.POST)
    public String addWorker(@Valid @ModelAttribute("worker") WorkerDTO workerDTO, BindingResult result,Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "workers/AddWorker";
            }
            System.out.println("insertWorker Post method ");
            workerService.add(workerDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/showallworkers";
    }

    @RequestMapping("/showallworkers")
    public ModelAndView ShowAllWorkers() {
        List<Worker> workers = workerService.findAll();
		if(workers == null)
			workers = new ArrayList<>();
        List<WorkerDTO> workerDTOs = convertToDTO(workers);
        return new ModelAndView("workers/showworkers", "workerlist", workerDTOs);
    }

    @RequestMapping(value = "/showSelectedWorker", method = RequestMethod.GET)
    public ModelAndView showWorker(@RequestParam("id") int id, Model model) {

        WorkerDTO workerDTO = workerService.findOne(id);
        model.addAttribute("worker", workerDTO);
        model.addAttribute("errorDiv", "none");
        return new ModelAndView("workers/updateWorker");
    }

    @RequestMapping("/deleteWorker")
    public String removeWorker(@RequestParam("id") int id) {
        workerService.removeWorker(id);
        return "redirect:/showallworkers";
    }

    @RequestMapping(value = "/showSelectedWorker", method = RequestMethod.POST)
    public String updateWorker(@Valid @ModelAttribute("worker") WorkerDTO workerDTO, BindingResult result,Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errorDiv", "block");
                return "workers/updateWorker";
            }
            workerService.updateWorker(workerDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/showallworkers";
    }

    private List<WorkerDTO> convertToDTO(List<Worker> workers) {
        List<WorkerDTO> result = new ArrayList<>();
        for (Worker worker : workers) {
            result.add(toDTO(worker));
        }
        return result;
    }

    private WorkerDTO toDTO(Worker worker) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(worker.getId());
        workerDTO.setName(worker.getUser().getName());
        for (Role role : worker.getUser().getRoleCollection()) {
            workerDTO.getRolesID().add(role.getId());
            workerDTO.getRolesName().add(role.getName());
        }
        workerDTO.setAge(worker.getAge());
        workerDTO.setHistory(worker.getHistory());
        workerDTO.setCafetriaID(worker.getCafetria().getId());
        workerDTO.setCafetriaName(worker.getCafetria().getName());
        return workerDTO;
    }

}
