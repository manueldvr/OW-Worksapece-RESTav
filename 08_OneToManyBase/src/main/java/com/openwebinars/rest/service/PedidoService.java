package com.openwebinars.rest.service;

import com.openwebinars.rest.modelo.Pedido;
import com.openwebinars.rest.repos.PedidoRepository;
import com.openwebinars.rest.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends BaseService<Pedido, Long, PedidoRepository> {
}
