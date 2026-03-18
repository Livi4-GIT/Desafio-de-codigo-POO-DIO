package com.seuprojeto.strategy;

import org.springframework.stereotype.Component;

@Component
public class CanceladoStrategy implements StatusStrategy {

    public String getStatus() {
        return "cancelado";
    }

    public String executar() {
        return "Pedido cancelado";
    }
}