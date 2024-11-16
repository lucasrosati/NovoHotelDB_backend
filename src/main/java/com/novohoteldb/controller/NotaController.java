package com.novohoteldb.controller;

import com.novohoteldb.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notas")
public class NotaController{

    @Autowired
    private NotaService notaService;

    @GetMapping("/listar")
    public List<Map<String, Object>> listarNotas(){
        return notaService.listarNotas();
    }

    @GetMapping("/{id_reserva}")
    public Map<String, Object> buscarNotaPorId(@PathVariable int id_reserva){
        return notaService.buscarNotaPorId(id_reserva);
    }

}
