package com.seuprojeto.controller;

import com.seuprojeto.service.PedidoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping("/{status}")
    public String consultarStatus(@PathVariable String status) {
        return service.processar(status);
    }
}