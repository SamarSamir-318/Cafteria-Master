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
import eg.gov.iti.cafetria.model.dal.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the Data Access layer. Simple huh? PLease note that no need for any
 * dao implementation. This is an interface!
 */
@Repository
public interface OrderRepository extends ITICRUDRepository<Order, Integer> {

//    final static String status="FINISHED";
    public List<Order> findByCustomerId(Integer id);

//    @Query("SELECT * FROM Order where Order.status <> 'FINISHED'")
    public List<Order> findByStatusNot(@Param("status") String status);
}
