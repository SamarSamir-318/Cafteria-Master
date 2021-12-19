/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.model.Custom.validator;

import eg.gov.iti.cafetria.model.dal.dao.CafetriaRepository;
import eg.gov.iti.cafetria.model.dal.dao.CategoryRepository;
import eg.gov.iti.cafetria.model.dal.dao.ItemRepository;
import eg.gov.iti.cafetria.model.dal.dao.OfferRepository;
import eg.gov.iti.cafetria.model.dal.dao.RowMaterialRepository;
import eg.gov.iti.cafetria.model.dal.domain.Cafetria;
import eg.gov.iti.cafetria.model.dal.domain.Category;
import eg.gov.iti.cafetria.model.dal.domain.Item;
import eg.gov.iti.cafetria.model.dal.domain.Offer;
import eg.gov.iti.cafetria.model.dal.domain.RowMaterial;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author abdelrhman galal
 */
public class UniqueValidator implements ConstraintValidator<UniqueFields, Object> {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CafetriaRepository cafetriaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private RowMaterialRepository rowMaterialRepository;

    private UniqueFields uniqueFields;

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext cvc) {
        boolean valid = false;
        try {
            String id = uniqueFields.id();
            String idValue = BeanUtils.getProperty(object, id);
            int value = -1;
            if (idValue != null) {
                value = Integer.parseInt(idValue);
            }
            for (String key : uniqueFields.properties()) {
                String keyValue = BeanUtils.getProperty(object, key);
                if (uniqueFields.classType().equals(Category.class)) {
                    if (categoryRepository != null) {
                        Category findByName = categoryRepository.findByName(keyValue);
                        if (findByName == null || findByName.getId() == value) {
                            valid = true;
                        }
                    } else {
                        valid = true;
                    }

                } else if (uniqueFields.classType().equals(Cafetria.class)) {
                    if (cafetriaRepository != null) {
                        Cafetria findByName = cafetriaRepository.findByName(keyValue);
                        if (findByName == null || findByName.getId() == value) {
                            valid = true;
                        }
                    } else {
                        valid = true;
                    }

                } else if (uniqueFields.classType().equals(Item.class)) {
                    System.out.println("object = " + itemRepository);
                    if (itemRepository != null) {
                        Item findByName = itemRepository.findByName(keyValue);
                        if (findByName == null || findByName.getId() == value) {
                            valid = true;
                        }
                    } else {
                        valid = true;
                    }
                } else if (uniqueFields.classType().equals(Offer.class)) {
                    if (offerRepository != null) {
                        Offer findByDesc = offerRepository.findByDesc(keyValue);
                        if (findByDesc == null || findByDesc.getId() == value) {
                            valid = true;
                        }
                    } else {
                        valid = true;
                    }

                } else if (uniqueFields.classType().equals(RowMaterial.class)) {
                    if (rowMaterialRepository != null) {
                        RowMaterial findByName = rowMaterialRepository.findByName(keyValue);
                        if (findByName == null || findByName.getId() == value) {
                            valid = true;
                        }
                    } else {
                        valid = true;
                    }

                }
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UniqueValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(UniqueValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(UniqueValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valid;
    }

    @Override
    public void initialize(UniqueFields uniqueFields) {
        System.out.println("In Validation Intialize");
        this.uniqueFields = uniqueFields;
    }
}