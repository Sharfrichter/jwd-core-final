package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.Logger;

import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException {
        final NassaContext nassaContext = new NassaContext();
        final ApplicationMenu menu = () -> nassaContext;
        final Supplier<ApplicationContext> applicationContextSupplier = menu::getApplicationContext;
        nassaContext.init();
        Logger.info("app has been started");
        return applicationContextSupplier::get;
    }
}
