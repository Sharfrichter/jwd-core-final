package com.epam.jwd.core_final.builder;

import com.epam.jwd.core_final.criteria.Criteria;

import java.lang.reflect.Field;

public abstract class CriteriaBuilder<T extends Criteria> {

    public CriteriaBuilder add(Object value) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(value.getClass())) {
                try {
                    field.setAccessible(true);
                    field.set(this, value);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public abstract T build();
}
