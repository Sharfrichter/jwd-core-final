package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.util.Logger;

public class UnknownEntityException extends RuntimeException {

    private final String entityName;
    private final Object[] args;

    public UnknownEntityException(String entityName) {

        super();
        this.entityName = entityName;
        this.args = null;
        Logger.error("Unknown entity exception "+entityName);
    }

    public UnknownEntityException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
        Logger.error("Unknown entity exception "+entityName);
    }

    @Override
    public String getMessage() {
        return entityName;
    }
}
