package com.openwebinars.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductoDTO {

    private String nombre;

    private float precio;
}
