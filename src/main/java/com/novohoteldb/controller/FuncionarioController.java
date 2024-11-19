package com.novohoteldb.controller;


import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import com.novohoteldb.dto.GerenteDTO;
import com.novohoteldb.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.novohoteldb.service.ClienteService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    private ClienteService clienteService;

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

    @PostMapping("/cadastrar/camareira")
    public ResponseEntity cadastrarCamareira(@RequestBody CadastroDTO cadastroDTO) {
        return ResponseEntity.status(200).body(funcionarioService.cadastrarCamareira(cadastroDTO));
    }

    @PostMapping("/cadastrar/servicosgerais")
    public ResponseEntity cadastrarServicosGerais(@RequestBody CadastroDTO cadastroDTO) {
        return ResponseEntity.status(200).body(funcionarioService.cadastrarServicosGerais(cadastroDTO));
    }

    @PostMapping("/cadastrar/recepcionista")
    public ResponseEntity cadastrarRecepcionista(@RequestBody CadastroDTO cadastroDTO) {
        return ResponseEntity.status(200).body(funcionarioService.cadastrarRecepcionista(cadastroDTO));
    }

    @PostMapping("/cadastro/endereco/{cpf}")
    public ResponseEntity cadastrarEndereco(@PathVariable String cpf, @RequestBody EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(200).body(clienteService.cadastrarEndereco(cpf, enderecoDTO));
    }

    @PutMapping("/atualizar/endereco/{cpf}")
    public ResponseEntity atualizarEndereco(@PathVariable String cpf, @RequestBody EnderecoDTO endereco) {
        clienteService.atualizarEndereco(cpf, endereco);
        return ResponseEntity.status(200).body("Endereço atualizado com sucesso.");
    }

    @PostMapping("/adicionar/gerente")
    public ResponseEntity adicionarGerente(@RequestBody GerenteDTO gerenteDTO){
        funcionarioService.adicionarGerente(gerenteDTO);
        return ResponseEntity.status(200).body("Gerente adicionado com sucesso.");
    }

    @GetMapping("/listar/gerentes")
    public List<Map<String, Object>> listarGerentes() {
        return funcionarioService.listarGerentes();
    }

}
