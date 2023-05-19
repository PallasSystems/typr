package uk.pallas.systems.typr.rest;

import org.springframework.beans.factory.annotation.Autowired;
import uk.pallas.systems.typr.services.FieldDefinitionServices;

public class AdminController {

    /** The backend service to retrieve. */
    @Autowired
    private FieldDefinitionServices services;

}
