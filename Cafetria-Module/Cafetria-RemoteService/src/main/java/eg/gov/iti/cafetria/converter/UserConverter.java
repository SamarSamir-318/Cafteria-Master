/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.converter;

import eg.gov.iti.cafetria.dto.CustomerDTO;
import eg.gov.iti.cafetria.dto.UserDTO;
import eg.gov.iti.cafetria.dto.WorkerDTO;
import eg.gov.iti.cafetria.model.dal.domain.Customer;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.model.dal.domain.Worker;

/**
 *
 * @author Administrator
 */
public class UserConverter {

    public static UserDTO convertToDto(User user, Customer customer, Worker worker) {
        UserDTO userdto = new UserDTO();
        userdto.setId(user.getId());
        userdto.setName(user.getName());
        if (customer != null) {
            userdto.setType("customer");
            userdto.setBalance(customer.getBalance());
        } else {
            userdto.setType("worker");
            userdto.setAge(worker.getAge());
            userdto.setHistory(worker.getHistory());
            if(worker.getCafetria() != null) {
                userdto.setCafetriaId(worker.getCafetria().getId());
                userdto.setCafetriaName(worker.getCafetria().getName());
            }
        }
        return userdto;
    }

    public static CustomerDTO convertCustomerFromDto(UserDTO userdto) {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(userdto.getId());
        customerDTO.setName(userdto.getName());
        customerDTO.setBalance(userdto.getBalance());
        return customerDTO;
    }

    public static WorkerDTO convertWorkerFromDto(UserDTO userdto) {
        WorkerDTO workerDTO=new WorkerDTO();
        workerDTO.setId(userdto.getId());
        workerDTO.setName(userdto.getName());
        workerDTO.setAge(userdto.getAge());
        workerDTO.setHistory(userdto.getHistory());
        workerDTO.setCafetriaID(userdto.getCafetriaId());
        workerDTO.setCafetriaName(userdto.getCafetriaName());
        return workerDTO;
    }
}
