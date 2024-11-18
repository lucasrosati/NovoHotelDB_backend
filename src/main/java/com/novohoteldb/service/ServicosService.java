package com.novohoteldb.service;

import com.novohoteldb.dto.LimpezaDTO;
import com.novohoteldb.dto.ManutencaoDTO;
import com.novohoteldb.repository.ServicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServicosService {

    @Autowired
    private ServicosRepository servicosRepository;

    public List<Map<String, Object>> listarLimpezas(){
        return servicosRepository.listarLimpezas();
    }

    public List<Map<String, Object>> consultarLimpezas(String id_funcionario){
        return servicosRepository.consultarLimpezas(id_funcionario);
    }

    public ResponseEntity<String> adicionarLimpeza(LimpezaDTO limpeza){
        servicosRepository.adicionarLimpeza(limpeza);
        return ResponseEntity.status(200).body("Limpeza adicionada com sucesso.");
    }

    public List<Map<String, Object>> listarManutencao(){
        return servicosRepository.listarManutencao();
    }

    public Map<String, Object> consultarServico(String id_funcionario){
        return servicosRepository.consultarServico(id_funcionario);
    }

    public ResponseEntity adicionarManutencao(ManutencaoDTO manutencao){
        servicosRepository.adicionarManutencao(manutencao);
        return ResponseEntity.status(200).body("Manutencao adicionada com sucesso.");
    }

    public List<Map<String, Object>> listarReservasEfetuadas(String id_funcionario){
        return servicosRepository.listarReservasEfetuadas(id_funcionario);
    }

}
