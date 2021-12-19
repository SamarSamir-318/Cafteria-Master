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
import eg.gov.iti.cafetria.model.dal.domain.RowMaterial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This is the Data Access layer. Simple huh? PLease note that no need for any
 * dao implementation. This is an interface!
 */
@Repository
public interface RowMaterialRepository extends ITICRUDRepository<RowMaterial, Integer> {

    @Query("select m from RowMaterial m where m.name = ?")
    public RowMaterial findByName(String name);
}
