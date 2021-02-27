package com.epam.jwd.core_final.builder;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.AbstractBaseEntity;
import com.epam.jwd.core_final.domain.BaseEntity;

public interface CriteriaBuilder<T extends Criteria> {

    public CriteriaBuilder add(Object value);

    public T build();
}
