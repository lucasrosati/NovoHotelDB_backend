package com.novohoteldb.controller;

import com.novohoteldb.service.QuartosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quartos")
public class QuartosController {

    @Autowired
    private QuartosService quartosService;

    @GetMapping("/listar")
    public List<Map<String, Object>> listarQuartos() {
        return quartosService.listarQuartos();
    }

    @GetMapping("/consultar/{numero}")
    public Map<String, Object> consultarQuarto(@PathVariable String numero) {
        return quartosService.consultarQuarto(numero);
    }

    @GetMapping("/reservas/{numero}")
    public List<Map<String, Object>> reservas(@PathVariable String numero) {
        return quartosService.verificarReservas(numero);
    }

}
