package com.seuprojeto.service;

import com.seuprojeto.strategy.StatusStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    private final Map<String, StatusStrategy> estrategias;

    public PedidoService(List<StatusStrategy> estrategiasList) {
        this.estrategias = new HashMap<>();
        for (StatusStrategy e : estrategiasList) {
            this.estrategias.put(e.getStatus(), e);
        }
    }

    public String processar(String status) {
        StatusStrategy strategy = estrategias.get(status.toLowerCase());

        if (strategy == null) {
            return "Status não encontrado";
        }

        return strategy.executar();
    }
}