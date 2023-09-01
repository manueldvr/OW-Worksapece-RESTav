package com.openwebinars.rest.controller;

import com.openwebinars.rest.error.PedidoNotFoundException;
import com.openwebinars.rest.modelo.Pedido;
import com.openwebinars.rest.service.PedidoService;
import com.openwebinars.rest.util.pagination.PaginationLinksUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    private final PaginationLinksUtils paginationLinksUtils;

    /**
     * Pedidos
     * @param pageable
     * @param request
     * @return response
     * @throws PedidoNotFoundException
     */
    @GetMapping("/")
    public ResponseEntity<?> pedidos(Pageable pageable, HttpServletRequest request) throws PedidoNotFoundException {
        Page<Pedido> result = pedidoService.findAll(pageable);
        if (result.isEmpty()) {
            throw new PedidoNotFoundException();
        } else {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(result, uriBuilder))
                    .body(result);
        }
    }
}
