package com.novohoteldb.service;


import com.novohoteldb.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}

