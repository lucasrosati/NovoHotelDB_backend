package com.novohoteldb.controller;

import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import com.novohoteldb.dto.PagamentoDTO;
import com.novohoteldb.dto.ReservaClienteRecepcionistaQuartoDTO;
import com.novohoteldb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public List<Map<String, Object>> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarPessoa(@RequestBody CadastroDTO cadastro) {
        return ResponseEntity.status(200).body(clienteService.cadastrarCliente(cadastro));
    }

    @PostMapping("/cadastro/endereco/{cpf}")
    public ResponseEntity cadastrarEndereco(@PathVariable String cpf, @RequestBody EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(200).body(clienteService.cadastrarEndereco(cpf, enderecoDTO));
    }

    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity excluirCliente(@PathVariable String cpf) {
        try {
            clienteService.excluirCliente(cpf);
            return ResponseEntity.status(200).body("Cliente excluído com sucesso.");
        } catch (RuntimeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{cpf}")
    public Map<String, Object> buscarCliente(@PathVariable String cpf) {
        return clienteService.buscarCliente(cpf);
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity atualizarCliente(@PathVariable String cpf, @RequestBody CadastroDTO cadastro) {
        clienteService.atualizarCliente(cpf, cadastro);
        return ResponseEntity.status(200).body("Cliente atualizado com sucesso.");
    }

    @PutMapping("/atualizar/endereco/{cpf}")
    public ResponseEntity atualizarEndereco(@PathVariable String cpf, @RequestBody EnderecoDTO endereco) {
        clienteService.atualizarEndereco(cpf, endereco);
        return ResponseEntity.status(200).body("Endereço atualizado com sucesso.");
    }

    @PostMapping("/cadastro/pagamento")
    public ResponseEntity cadastrarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        clienteService.cadastrarPagamento(pagamentoDTO);
        return ResponseEntity.status(200).body("Pagamento cadastrado com sucesso.");
    }

}
