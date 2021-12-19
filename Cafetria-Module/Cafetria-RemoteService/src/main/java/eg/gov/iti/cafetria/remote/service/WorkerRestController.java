/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.remote.service;

import eg.gov.iti.cafetria.dto.JResponse;
import eg.gov.iti.cafetria.dto.WorkerDTO;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.model.dal.domain.Worker;
import eg.gov.iti.cafetria.service.UserService;
import eg.gov.iti.cafetria.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
public class WorkerRestController {

    @Autowired
    UserService userService;
    @Autowired
    WorkerService workerService;

    @RequestMapping(value = "/addOrUpdateWorker", method = RequestMethod.GET)
    public @ResponseBody
    JResponse addworker(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam int age, @RequestParam String history) {
        try {
            User user = userService.findOne(id);
            if(user != null) {
                user.setName(name);
                user.setEmail(email);
                Worker worker = user.getWorker();
                worker.setAge(age);
                worker.setHistory(history);
                worker.setUser(user);
                userService.updateUser(user);
                workerService.updateWorker(worker);
                return new JResponse(1, "worker updated successfully");
            } else {
                WorkerDTO workerDTO = new WorkerDTO();
                workerDTO.setId(id);
                workerDTO.setName(name);
                workerDTO.setAge(age);
                workerDTO.setEmail(email);
                workerDTO.setHistory(history);
                workerService.add(workerDTO);
                return new JResponse(1, "worker added successfully");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JResponse(0, "problem occured while adding worker");
        }
    }

    @RequestMapping(value = "/removeWorker", method = RequestMethod.GET)
    public @ResponseBody
    JResponse removeWorker(@RequestParam int id) {
       try{
           workerService.removeWorker(id);
           return new JResponse(1, "worker is removed successfully"); 
       }catch (Exception ex) {
            ex.printStackTrace();
            return new JResponse(0, "problem occured while removing worker");
        }
    }

}
