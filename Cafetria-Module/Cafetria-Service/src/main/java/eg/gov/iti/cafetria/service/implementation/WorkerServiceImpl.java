/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service.implementation;

/**
 *
 * @author nour
 */
import eg.gov.iti.cafetria.model.dal.dao.CafetriaRepository;
import eg.gov.iti.cafetria.model.dal.dao.RoleRepository;
import eg.gov.iti.cafetria.model.dal.dao.UserRepository;
import eg.gov.iti.cafetria.model.dal.dao.WorkerRepository;
import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.model.dal.domain.Role;
import eg.gov.iti.cafetria.model.dal.domain.User;
import eg.gov.iti.cafetria.model.dal.domain.Worker;
import eg.gov.iti.cafetria.service.WorkerService;
import eg.gov.iti.cafetria.dto.WorkerDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CafetriaRepository cafetriaRepository;

    @Transactional
    @Override
    public void add(WorkerDTO workerdto) {
        Worker worker = new Worker();
        worker.setAge(workerdto.getAge());
        worker.setHistory(workerdto.getHistory());
        Cafetria cafetria = null;
        if (workerdto.getCafetriaID() != null) {
            cafetria = cafetriaRepository.findOne(workerdto.getCafetriaID());
            worker.setCafetria(cafetria);
        }
        User user = new User();
        user.setId(workerdto.getId());
        user.setType(false);
        user.setName(workerdto.getName());
        user.setEmail(workerdto.getEmail());
        user.setRoleCollection(new ArrayList<Role>());
        for (Integer roleID : workerdto.getRolesID()) {
            Role role = roleRepository.findOne(roleID);
            if (role != null) {
                user.getRoleCollection().add(role);
            }
        }
        User newUser = userRepository.save(user);
        for (Role role : user.getRoleCollection()) {
            if (role.getUserCollection() == null) {
                role.setUserCollection(new ArrayList<User>());
            }
            role.getUserCollection().add(newUser);
            roleRepository.save(role);
        }
        worker.setUser(newUser);
        workerRepository.save(worker);
        if (cafetria != null) {
            if (cafetria.getWorkerCollection() == null) {
                cafetria.setWorkerCollection(new ArrayList<Worker>());
            }
            cafetria.getWorkerCollection().add(worker);
            cafetriaRepository.save(cafetria);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    @Transactional
    @Override
    public void addAll(Collection<WorkerDTO> workersdto) {
        for (WorkerDTO workerdto : workersdto) {
            add(workerdto);
        }
    }

    @Override
    public Worker getWorker(Integer ID) {
        return workerRepository.getOne(ID);
    }

    @Override
    public void removeWorker(Integer ID) {
        workerRepository.delete(ID);
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            for (User user : role.getUserCollection()) {
                if (user.getId() == ID) {
                    role.getUserCollection().remove(user);
                    roleRepository.save(role);
                    break;
                }
            }
        }
        userRepository.delete(ID);
    }

    @Override
    public void updateWorker(WorkerDTO workerdto) {
        Integer i = workerdto.getId();
        Worker worker = workerRepository.getOne(i);
        worker.setAge(workerdto.getAge());
        worker.setHistory(workerdto.getHistory());
        Cafetria cafetria = cafetriaRepository.findOne(workerdto.getCafetriaID());
        if (cafetria != null) {
            worker.setCafetria(cafetria);
        }
        User user = userRepository.getOne(workerdto.getId());
        user.setName(workerdto.getName());
        for (Role role : user.getRoleCollection()) {
            role.getUserCollection().remove(user);
            roleRepository.save(role);
        }
        user.setRoleCollection(new ArrayList<Role>());
        User newUser = userRepository.save(user);
        if (workerdto.getRolesID() != null) {
            newUser.setRoleCollection(new ArrayList<Role>());
            for (Integer roleID : workerdto.getRolesID()) {
                Role role = roleRepository.findOne(roleID);
                if (role != null) {
                    newUser.getRoleCollection().add(role);
                }
            }
        }
        newUser = userRepository.save(newUser);
        for (Role role : newUser.getRoleCollection()) {
            if (role.getUserCollection() == null) {
                role.setUserCollection(new ArrayList<User>());
            }
            role.getUserCollection().add(newUser);
            roleRepository.save(role);
        }
        worker.setUser(newUser);
        workerRepository.save(worker);
    }

    @Override
    public WorkerDTO findOne(Integer id) {
        Worker worker = workerRepository.getOne(id);
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setAge(worker.getAge());
        workerDTO.setHistory(worker.getHistory());
        workerDTO.setId(id);
        workerDTO.setName(worker.getUser().getName());
        for (Role role : worker.getUser().getRoleCollection()) {
            workerDTO.getRolesID().add(role.getId());
        }
        return workerDTO;
    }

    @Override
    public void updateWorker(Worker worker) {
        workerRepository.save(worker);
    }
}
