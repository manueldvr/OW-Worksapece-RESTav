package com.openwebinars.rest.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "CiudadPaisProj", types = {Ciudad.class})
public interface CiudadPaisProj {

    String getNombre();

    @Value("#{target.getPais().getNombre()}")
    String getPais();
}
