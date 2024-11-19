package com.novohoteldb.repository;


import com.novohoteldb.dto.CadastroDTO;
import com.novohoteldb.dto.EnderecoDTO;
import com.novohoteldb.dto.GerenteDTO;
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

    public void cadastrarCamareira(CadastroDTO cadastro){
        String sql =  "INSERT into PESSOA(cpf,nome,email,telefone1,telefone2) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,cadastro.cpf(),cadastro.nome(),cadastro.email(),cadastro.telefone1(),cadastro.telefone2());
        String sql2 = "INSERT INTO FUNCIONARIO(fk_Pessoa_CPF) VALUES (?)";
        jdbcTemplate.update(sql2, cadastro.cpf());
        int id_funcionario = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        String sql3 = "INSERT INTO CAMAREIRA(fk_Funcionario_Id_Funcionario) VALUES (?)";
        jdbcTemplate.update(sql3, id_funcionario);
    }

    public void cadastrarServicosGerais(CadastroDTO cadastro){
        String sql =  "INSERT into PESSOA(cpf,nome,email,telefone1,telefone2) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,cadastro.cpf(),cadastro.nome(),cadastro.email(),cadastro.telefone1(),cadastro.telefone2());
        String sql2 = "INSERT INTO FUNCIONARIO(fk_Pessoa_CPF) VALUES (?)";
        jdbcTemplate.update(sql2, cadastro.cpf());
        int id_funcionario = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        String sql3 = "INSERT INTO SERVICOS_GERAIS(fk_Funcionario_Id_Funcionario) VALUES (?)";
        jdbcTemplate.update(sql3, id_funcionario);
    }

    public void cadastrarRecepcionista(CadastroDTO cadastro){
        String sql =  "INSERT into PESSOA(cpf,nome,email,telefone1,telefone2) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,cadastro.cpf(),cadastro.nome(),cadastro.email(),cadastro.telefone1(),cadastro.telefone2());
        String sql2 = "INSERT INTO FUNCIONARIO(fk_Pessoa_CPF) VALUES (?)";
        jdbcTemplate.update(sql2, cadastro.cpf());
        int id_funcionario = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        String sql3 = "INSERT INTO RECEPCIONISTA(fk_Funcionario_Id_Funcionario, Checkins_Efetuados) VALUES (?, ?)";
        jdbcTemplate.update(sql3, id_funcionario, 0);
    }

    public void adicionarGerente(GerenteDTO gerente){
        String sql = "INSERT INTO GERENCIA(fk_Funcionario_Id_Funcionario, fk_Gerenciado_Id_Funcionario) VALUES(?, ?)";
        jdbcTemplate.update(sql, gerente.gerente_id(),gerente.gerenciado_id());
    }

    public List<Map<String, Object>> listarGerentes(){
        return jdbcTemplate.queryForList("select f.Id_Funcionario AS ID_GERENTE, p2.Nome AS NOME_GERENTE,f2.Id_Funcionario AS ID_GERENCIADO, p.Nome AS NOME_GERENCIADO \n" +
                "from gerencia g \n" +
                "join funcionario f on g.fk_Funcionario_Id_Funcionario = f.Id_Funcionario\n" +
                "join funcionario f2 on g.fk_Gerenciado_Id_Funcionario = f2.Id_Funcionario \n" +
                "join pessoa p2 on p2.CPF = f.fk_Pessoa_CPF\n" +
                "join pessoa p on p.CPF = f2.fk_Pessoa_CPF \n");
    }

    public void cadastrarEndereco(String cpf , EnderecoDTO endereco) {
        String sql = "INSERT INTO ENDERECO(Rua, Numero, Bairro, Cep, fk_Pessoa_Cpf) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, endereco.Rua(), endereco.Numero(), endereco.Bairro(), endereco.Cep(), cpf);
        String updatePessoa = "UPDATE PESSOA SET fk_Endereco_PK = ? WHERE CPF = ?";
        int id_endereco = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        jdbcTemplate.update(updatePessoa, id_endereco, cpf);
    }

    public void atualizarEndereco(String cpf, EnderecoDTO endereco) {
        String sql = "UPDATE ENDERECO SET Rua = ?, Numero = ?, Bairro = ?, Cep = ? WHERE fk_Pessoa_CPF = ?";
        jdbcTemplate.update(sql, endereco.Rua(), endereco.Numero(), endereco.Bairro(), endereco.Cep(), cpf);
    }

}
