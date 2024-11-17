package com.novohoteldb.controller;


import com.novohoteldb.repository.FuncionarioRepository;
import com.novohoteldb.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/listar")
    public List<Map<String, Object>> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/camareira")
    public List<Map<String, Object>> checarCamareira() {
        return funcionarioService.checarCamareira();
    }

    @GetMapping("/servicosgerais")
    public List<Map<String, Object>> checarServicosGerais() {
        return funcionarioService.checarServicosGerais();
    }

    @GetMapping("/recepcionista")
    public List<Map<String, Object>> checarRecepcionista() {
        return funcionarioService.checarRecepcionista();
    }

}
