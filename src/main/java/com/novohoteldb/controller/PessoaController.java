package com.novohoteldb.controller;

import com.novohoteldb.dto.CadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.novohoteldb.service.PessoaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Map<String, Object>> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{cpf}")
    public Map<String, Object> listarPessoasPorCpf(@PathVariable String cpf) {
        return pessoaService.listarPessoasPorCpf(cpf);
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarPessoa(@RequestBody CadastroDTO cadastro) {
        return ResponseEntity.status(200).body(pessoaService.cadastrarPessoa(cadastro));
    }

}
