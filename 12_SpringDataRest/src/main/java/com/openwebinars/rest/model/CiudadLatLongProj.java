package com.openwebinars.rest.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ciudadLatLong", types = {Ciudad.class})
public interface CiudadLatLongProj {

    //@Value("#{target.id}")
    long getId();

    @Value("#{target.id}#{','}#{target.nombre}")
    String getIdYNombre();

    String getNombre();

    @Value("#{target.lat}#{','}#{target.lng}")
    String getCoordenadas();

    @Value("#{target.getPais().getNombre()}")
    String getPais();
}
