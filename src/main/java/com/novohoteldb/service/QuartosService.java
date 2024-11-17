package com.novohoteldb.service;

import com.novohoteldb.repository.QuartosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuartosService {

    @Autowired
    private QuartosRepository quartosRepository;

    public List<Map<String, Object>> listarQuartos() {
        return quartosRepository.listarQuartos();
    }

    public Map<String, Object> consultarQuarto(String numero) {
        return quartosRepository.consultarQuarto(numero);
    }

    public List<Map<String, Object>> verificarReservas(String numero){
        return quartosRepository.verificarReservas(numero);
    }

}
