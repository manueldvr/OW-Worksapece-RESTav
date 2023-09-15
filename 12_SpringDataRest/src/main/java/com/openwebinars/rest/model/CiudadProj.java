package com.openwebinars.rest.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ciudadSinUbicaction", types={Ciudad.class})
public interface CiudadProj {

    String getNombre();
}
