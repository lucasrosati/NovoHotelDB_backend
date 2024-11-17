package com.novohoteldb.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FuncionarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> ListarFuncionarios() {
        String sql = "select * from funcionario f join pessoa p on f.fk_Pessoa_CPF = p.CPF ";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>>checarCamareira() {
        String sql = "select * from camareira c join funcionario f on c.fk_Funcionario_Id_Funcionario = f.Id_Funcionario join pessoa p on f.fk_Pessoa_CPF = p.CPF";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> checarServicosGerais() {
        String sql = "select * from servicos_gerais s join funcionario f on s.fk_Funcionario_Id_Funcionario = f.Id_Funcionario join pessoa p on f.fk_Pessoa_CPF = p.CPF";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> checarRecepcionista() {
        String sql = "select * from recepcionista r join funcionario f on r.fk_Funcionario_Id_Funcionario = f.Id_Funcionario join pessoa p on f.fk_Pessoa_CPF = p.CPF";
        return jdbcTemplate.queryForList(sql);
    }

}

