/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.dal.dao;

/**
 *
 * @author ibrahiem
 */
import eg.gov.iti.cafetria.model.dal.domain.Privilege;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is the Data Access layer. Simple huh? PLease note that no need for any
 * dao implementation. This is an interface!
 */
@Repository
public interface PrivilegeRepository extends ITICRUDRepository<Privilege, Integer> {

    @Query("select pr from Privilege pr inner join pr.roleCollection role "
            + "inner join role.userCollection user "
            + "where user.email = ?")
    public List<Privilege> findAll(String email);
}
