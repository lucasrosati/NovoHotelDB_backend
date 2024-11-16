package com.novohoteldb.service;

import com.novohoteldb.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Map<String, Object>> listarNotas(){
        return notaRepository.listarNotas();
    }

    public Map<String, Object> buscarNotaPorId(Integer id_reserva){
        return notaRepository.buscarNotaPorId(id_reserva);
    }

}
