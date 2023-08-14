package com.openwebinars.rest.error;

public class SearchProductoNoResultException extends RuntimeException{

    public SearchProductoNoResultException() {
        super("La busqueda de productos no produjo resultados");
    }

    public SearchProductoNoResultException(String msg) {
        super(String.format("El termino de busqueda %s no produjo resultados", msg));
    }
}
