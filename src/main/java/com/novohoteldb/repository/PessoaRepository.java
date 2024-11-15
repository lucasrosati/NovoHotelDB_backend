package com.novohoteldb.repository;

import com.novohoteldb.dto.CadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PessoaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String,Object>> listarPessoas(){
        String sql = "select * from pessoa";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String,Object> listarPessoasPorCPF(String cpf){
        String sql = "select * from pessoa WHERE CPF = ?";
        return jdbcTemplate.queryForMap(sql,cpf);
    }

    public void cadastrarPessoa(CadastroDTO cadastro) {
        String sql =  "INSERT into PESSOA(cpf,nome,email,telefone1,telefone2) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,cadastro.cpf(),cadastro.nome(),cadastro.email(),cadastro.telefone1(),cadastro.telefone2());
    }
}
