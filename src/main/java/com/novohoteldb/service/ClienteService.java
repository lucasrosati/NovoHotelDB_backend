package com.novohoteldb.service;

import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import com.novohoteldb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Map<String, Object>> listarClientes() {
        return clienteRepository.listarClientes();
    }

    public ResponseEntity cadastrarCliente(CadastroDTO cadastro) {
        clienteRepository.cadastrarCliente(cadastro);
        return ResponseEntity.status(200).body("Pessoa cadastrada com sucesso!");
    }

    public ResponseEntity cadastrarEndereco(String cpf , EnderecoDTO endereco) {
        clienteRepository.cadastrarEndereco(cpf, endereco);
        return ResponseEntity.status(200).body("Endereco cadastrada com sucesso!");
    }

    public ResponseEntity excluirCliente(String cpf) {
        clienteRepository.excluirCliente(cpf);
        return ResponseEntity.status(200).body("Cliente Cliente excluído com sucesso.");
    }

    public Map<String, Object> buscarCliente(String cpf) {
        return clienteRepository.buscarCliente(cpf);
    }

    public ResponseEntity atualizarCliente(String cpf, CadastroDTO cadastro) {
        clienteRepository.atualizarCliente(cpf, cadastro);
        return ResponseEntity.status(200).body("Cliente atualizado com sucesso!");
    }

    public ResponseEntity atualizarEndereco(String cpf, EnderecoDTO endereco) {
        clienteRepository.atualizarEndereco(cpf, endereco);
        return ResponseEntity.status(200).body("Endereço cadastrada com sucesso!");
    }

}
