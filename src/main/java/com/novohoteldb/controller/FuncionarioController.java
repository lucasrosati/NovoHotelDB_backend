package com.novohoteldb.controller;


import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import com.novohoteldb.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/cadastro/endereco/{cpf}")
//    public ResponseEntity cadastrarEndereco(@PathVariable String cpf, @RequestBody EnderecoDTO enderecoDTO) {
//        return ResponseEntity.status(200).body(clienteService.cadastrarEndereco(cpf, enderecoDTO));
//    }
//
//    @DeleteMapping("/excluir/{cpf}")
//    public ResponseEntity excluirCliente(@PathVariable String cpf) {
//        try {
//            clienteService.excluirCliente(cpf);
//            return ResponseEntity.status(200).body("Cliente excluído com sucesso.");
//        } catch (RuntimeException e){
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/buscar/{cpf}")
//    public Map<String, Object> buscarCliente(@PathVariable String cpf) {
//        return clienteService.buscarCliente(cpf);
//    }
//
//    @PutMapping("/atualizar/{cpf}")
//    public ResponseEntity atualizarCliente(@PathVariable String cpf, @RequestBody CadastroDTO cadastro) {
//        clienteService.atualizarCliente(cpf, cadastro);
//        return ResponseEntity.status(200).body("Cliente atualizado com sucesso.");
//    }
//
//    @PutMapping("/atualizar/endereco/{cpf}")
//    public ResponseEntity atualizarEndereco(@PathVariable String cpf, @RequestBody EnderecoDTO endereco) {
//        clienteService.atualizarEndereco(cpf, endereco);
//        return ResponseEntity.status(200).body("Endereço atualizado com sucesso.");
//    }

}
