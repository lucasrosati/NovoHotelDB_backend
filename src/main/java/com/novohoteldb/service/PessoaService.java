package com.novohoteldb.service;

import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.novohoteldb.repository.PessoaRepository;

import java.util.List;
import java.util.Map;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Map<String, Object>> listarPessoas() {
        return pessoaRepository.listarPessoas();
    }

    public Map<String, Object> listarPessoasPorCpf(String cpf) {
        return pessoaRepository.listarPessoasPorCPF(cpf);

    }

    public ResponseEntity cadastrarPessoa(CadastroDTO cadastro) {
        pessoaRepository.cadastrarPessoa(cadastro);
        return ResponseEntity.status(200).body("Pessoa cadastrada com sucesso!");
    }

    public ResponseEntity cadastrarEndereco(String cpf ,EnderecoDTO endereco) {
        pessoaRepository.cadastrarEndereco(cpf, endereco);
        return ResponseEntity.status(200).body("Endereco cadastrada com sucesso!");
    }

}
