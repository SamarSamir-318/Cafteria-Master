/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.service;

import eg.gov.iti.cafetria.dto.RowMaterialDTO;
import eg.gov.iti.cafetria.model.dal.domain.RowMaterial;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Masoud
 */
public interface RowMaterialService {

    public RowMaterialDTO findOne(Integer id);

    public List<RowMaterialDTO> getAll();

    public void delete(Integer id);

    public void insert(RowMaterialDTO rowMaterialDTO);

    public void update(RowMaterialDTO rowMaterialDTO);

    public boolean exists(Integer id);

    public void addAll(Collection<RowMaterial> rowMaterials);

}
