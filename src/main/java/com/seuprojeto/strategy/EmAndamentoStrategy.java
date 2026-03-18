package com.seuprojeto.strategy;

import org.springframework.stereotype.Component;

@Component
public class EmAndamentoStrategy implements StatusStrategy {

    public String getStatus() {
        return "andamento";
    }

    public String executar() {
        return "Pedido em andamento";
    }
}