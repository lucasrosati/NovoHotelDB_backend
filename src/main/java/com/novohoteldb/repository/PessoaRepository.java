package com.novohoteldb.repository;

import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
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

    public void cadastrarEndereco(String cpf ,EnderecoDTO endereco) {
        String sql = "INSERT INTO ENDERECO(Rua, Numero, Bairro, Cep, fk_Pessoa_Cpf) values(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, endereco.Rua(), endereco.Numero(), endereco.Bairro(), endereco.Cep(), endereco.fk_Pessoa_CPF());
        String updatePessoa = "UPDATE PESSOA SET fk_Endereco_PK = ? WHERE CPF = ?";
        int id_endereco = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        jdbcTemplate.update(updatePessoa, id_endereco, cpf);
    }

}
