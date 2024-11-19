package com.novohoteldb.controller;

import com.novohoteldb.dto.LimpezaDTO;
import com.novohoteldb.dto.ManutencaoDTO;
import com.novohoteldb.service.ServicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servicos")
public class ServicosController {

    @Autowired
    private ServicosService servicosService;

    @GetMapping("limpeza/listar")
    public List<Map<String, Object>> listarLimpezas(){
        return servicosService.listarLimpezas();
    }

    @GetMapping("/limpeza/{id_funcionario}")
    public List<Map<String, Object>> consultarLimpezas(@PathVariable String id_funcionario){
        return servicosService.consultarLimpezas(id_funcionario);
    }

    @PostMapping("/limpeza/adicionar")
    public ResponseEntity adicionarLimpeza(@RequestBody LimpezaDTO limpeza){
        servicosService.adicionarLimpeza(limpeza);
        return ResponseEntity.status(200).body("Limpeza adicionada com sucesso");
    }

    @GetMapping("/manutencao/listar")
    public List<Map<String, Object>> listarManutencao(){
        return servicosService.listarManutencao();
    }

    @GetMapping("/manutencao/{id_funcionario}")
    public Map<String, Object> consultarServico(@PathVariable String id_funcionario){
        return servicosService.consultarServico(id_funcionario);
    }

    @GetMapping("/manutencao/adicionar")
    public ResponseEntity adicionarManutencao(@RequestBody ManutencaoDTO manutencao){
        servicosService.adicionarManutencao(manutencao);
        return ResponseEntity.status(200).body("Serviço cadastrado com sucesso");
    }

    @GetMapping("/reservas/listar/{id_funcionario}")
    public List<Map<String, Object>> listarReservasEfetuadas(@PathVariable String id_funcionario){
        return servicosService.listarReservasEfetuadas(id_funcionario);
    }

}
