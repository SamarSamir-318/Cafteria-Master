/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

/**
 *
 * @author nour
 */
import eg.gov.iti.cafetria.model.dal.domain.Worker;
import eg.gov.iti.cafetria.dto.WorkerDTO;
import java.util.Collection;
import java.util.List;

public interface WorkerService {

    public WorkerDTO findOne(Integer id);

    public void add(WorkerDTO worker);

    public List<Worker> findAll();

    public void addAll(Collection<WorkerDTO> workers);

    public Worker getWorker(Integer ID);

    public void removeWorker(Integer ID);

    public void updateWorker(WorkerDTO worker);

    public void updateWorker(Worker worker);
}
