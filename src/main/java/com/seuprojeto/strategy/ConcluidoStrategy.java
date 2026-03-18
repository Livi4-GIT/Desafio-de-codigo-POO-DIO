package com.seuprojeto.strategy;

import org.springframework.stereotype.Component;

@Component
public class ConcluidoStrategy implements StatusStrategy {

    public String getStatus() {
        return "concluido";
    }

    public String executar() {
        return "Pedido concluído";
    }
}