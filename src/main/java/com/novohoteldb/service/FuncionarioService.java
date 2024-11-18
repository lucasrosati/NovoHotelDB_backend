package com.novohoteldb.service;


import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Map<String, Object>> listarFuncionarios() {return funcionarioRepository.ListarFuncionarios();}

    public List<Map<String, Object>> checarCamareira() {return funcionarioRepository.checarCamareira();}

    public List<Map<String, Object>> checarServicosGerais() {return funcionarioRepository.checarServicosGerais();}

    public List<Map<String, Object>> checarRecepcionista() {return funcionarioRepository.checarRecepcionista();}

    public ResponseEntity cadastrarCamareira(CadastroDTO cadastro){
        funcionarioRepository.cadastrarCamareira(cadastro);
        return ResponseEntity.status(200).body("Camareira cadastrada com sucesso!");
    }

    public ResponseEntity cadastrarServicosGerais(CadastroDTO cadastro){
        funcionarioRepository.cadastrarServicosGerais(cadastro);
        return ResponseEntity.status(200).body("Serviços Gerais cadastrado com sucesso!");
    }

    public ResponseEntity cadastrarRecepcionista(CadastroDTO cadastro){
        funcionarioRepository.cadastrarRecepcionista(cadastro);
        return ResponseEntity.status(200).body("Recepcionista cadastrado com sucesso!");
    }

}

