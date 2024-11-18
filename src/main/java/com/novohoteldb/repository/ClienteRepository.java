package com.novohoteldb.repository;

import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> listarClientes() {
        return jdbcTemplate.queryForList("select *\n" +
                "from cliente c \n" +
                "join pessoa p ON c.fk_Pessoa_CPF = p.CPF \n" +
                "join endereco e on e.Endereco_PK = p.fk_Endereco_PK ");
    }

    public void cadastrarCliente(CadastroDTO cadastro) {
        String sql =  "INSERT into PESSOA(cpf,nome,email,telefone1,telefone2) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,cadastro.cpf(),cadastro.nome(),cadastro.email(),cadastro.telefone1(),cadastro.telefone2());
        jdbcTemplate.update("INSERT INTO CLIENTE (fk_Pessoa_CPF) values(?)",cadastro.cpf());
    }

    public void cadastrarEndereco(String cpf , EnderecoDTO endereco) {
        String sql = "INSERT INTO ENDERECO(Rua, Numero, Bairro, Cep, fk_Pessoa_Cpf) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, endereco.Rua(), endereco.Numero(), endereco.Bairro(), endereco.Cep(), cpf);
        String updatePessoa = "UPDATE PESSOA SET fk_Endereco_PK = ? WHERE CPF = ?";
        int id_endereco = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        jdbcTemplate.update(updatePessoa, id_endereco, cpf);
    }

    public void excluirCliente(String cpf) {
        String sql = "DELETE FROM PESSOA WHERE CPF = ?";
        String sql2 = "DELETE FROM CLIENTE WHERE fk_Pessoa_CPF = ?";
        String sql3 = "DELETE FROM ENDERECO WHERE fk_Pessoa_CPF = ?";

        jdbcTemplate.update(sql2, cpf);
        jdbcTemplate.update(sql3, cpf);
        jdbcTemplate.update(sql, cpf);

    }

    public Map<String, Object> buscarCliente(String cpf) {
        String sql = "select * from cliente c \n" +
                "join pessoa p ON c.fk_Pessoa_CPF = p.CPF \n" +
                "join endereco e on e.Endereco_PK = p.fk_Endereco_PK " +
                "WHERE p.CPF = ?";
        return jdbcTemplate.queryForMap(sql, cpf);

    }

    public void atualizarCliente(String cpf, CadastroDTO cadastro) {
        String sql = "UPDATE PESSOA SET Email = ?, TELEFONE1 = ?, TELEFONE2 = ? WHERE CPF = ?";
        jdbcTemplate.update(sql, cadastro.email(), cadastro.telefone1(), cadastro.telefone2(), cpf);
    }

    public void atualizarEndereco(String cpf, EnderecoDTO endereco) {
        String sql = "UPDATE ENDERECO SET Rua = ?, Numero = ?, Bairro = ?, Cep = ? WHERE fk_Pessoa_CPF = ?";
        jdbcTemplate.update(sql, endereco.Rua(), endereco.Numero(), endereco.Bairro(), endereco.Cep(), cpf);
    }

}
