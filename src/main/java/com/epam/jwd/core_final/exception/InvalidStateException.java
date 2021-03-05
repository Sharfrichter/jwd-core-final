package com.epam.jwd.core_final.exception;

import com.epam.jwd.core_final.util.Logger;

public class InvalidStateException extends Exception {

    public InvalidStateException(String message) {
        super(message);
        Logger.error("Invalid state exception " + message);
    }
}
