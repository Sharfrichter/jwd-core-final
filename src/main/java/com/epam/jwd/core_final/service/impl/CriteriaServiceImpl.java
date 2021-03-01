package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.service.CriteriaService;

import java.lang.reflect.Field;

public class CriteriaServiceImpl implements CriteriaService {
    Criteria criteria;

    public CriteriaServiceImpl(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean isSuitable(BaseEntity baseEntity) {
        Field[] fields=criteria.getClass().getDeclaredFields();
        int filters=0;
        for(Field field:fields){
            field.setAccessible(true);
            try {
                if(field.get(criteria)!=null){
                    filters++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int counter=0;
        for(Field field:fields){
            try {
                field.setAccessible(true);
                Field baseEntityField=baseEntity.getClass().getDeclaredField(field.getName());
                baseEntityField.setAccessible(true);
                if(baseEntityField.get(baseEntity).equals(field.get(criteria))){
                    counter++;
                }
                baseEntityField.setAccessible(false);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            field.setAccessible(false);
        }
        if(counter==filters){
           return true;
        }
        return false;
    }
}
