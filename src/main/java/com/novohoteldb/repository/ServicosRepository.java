package com.novohoteldb.repository;

import com.novohoteldb.dto.LimpezaDTO;
import com.novohoteldb.dto.ManutencaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ServicosRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> listarLimpezas() {
        return jdbcTemplate.queryForList("select p.Nome , f.Id_Funcionario , l.fk_Quarto_Numero\n" +
                "from limpa l\n" +
                "join camareira c on l.fk_Camareira_fk_Funcionario_Id_Funcionario = c.fk_Funcionario_Id_Funcionario \n" +
                "join funcionario f on c.fk_Funcionario_Id_Funcionario = f.Id_Funcionario \n" +
                "join pessoa p on p.CPF = f.fk_Pessoa_CPF ");
    }

    public List<Map<String, Object>> consultarLimpezas(String id_funcionario) {
        String sql = "select p.Nome , f.Id_Funcionario , l.fk_Quarto_Numero\n" +
                "from limpa l\n" +
                "join camareira c on l.fk_Camareira_fk_Funcionario_Id_Funcionario = c.fk_Funcionario_Id_Funcionario \n" +
                "join funcionario f on c.fk_Funcionario_Id_Funcionario = f.Id_Funcionario \n" +
                "join pessoa p on p.CPF = f.fk_Pessoa_CPF \n" +
                "where f.Id_Funcionario = ?";

        return jdbcTemplate.queryForList(sql, id_funcionario);
    }

    public void adicionarLimpeza(LimpezaDTO limpezaDTO) {
        String sql = "insert into limpa(fk_Quarto_Numero, fk_Camareira_fk_Funcionario_Id_Funcionario) values(?,?)";
        jdbcTemplate.update(sql, limpezaDTO.numero(), limpezaDTO.id());
        String sql2 = "UPDATE QUARTO SET STATUS = 1 WHERE ?";
        jdbcTemplate.update(sql2, limpezaDTO.numero());
    }

    public List<Map<String, Object>> listarManutencao() {
        return jdbcTemplate.queryForList("select p.Nome , f.Id_Funcionario , m.fk_Quarto_Numero \n" +
                "from mantem m \n" +
                "join servicos_gerais sg on sg.fk_Funcionario_Id_Funcionario = m.fk_Servicos_Gerais_fk_Funcionario_Id_Funcionario \n" +
                "join funcionario f on sg.fk_Funcionario_Id_Funcionario = f.Id_Funcionario \n" +
                "join pessoa p on f.fk_Pessoa_CPF = p.CPF ");
    }

    public List<Map<String, Object>> consultarServico(String id_funcionario) {
        String sql = "select p.Nome , f.Id_Funcionario , m.fk_Quarto_Numero \n" +
                "from mantem m \n" +
                "join servicos_gerais sg on sg.fk_Funcionario_Id_Funcionario = m.fk_Servicos_Gerais_fk_Funcionario_Id_Funcionario \n" +
                "join funcionario f on sg.fk_Funcionario_Id_Funcionario = f.Id_Funcionario \n" +
                "join pessoa p on f.fk_Pessoa_CPF = p.CPF \n" +
                "where f.Id_Funcionario = ?";

        return jdbcTemplate.queryForList(sql, id_funcionario);
    }

    public void adicionarManutencao(ManutencaoDTO manutencao) {
        String sql = "INSERT INTO MANTEM(fk_Quarto_Numero, fk_Servicos_Gerais_fk_Funcionario_Id_Funcionario) VALUES(?,?)";
        jdbcTemplate.update(sql, manutencao.numero(), manutencao.id());
        String sql2 = "UPDATE QUARTO SET STATUS = 1 WHERE ?";
        jdbcTemplate.update(sql2, manutencao.numero());
    }

    public List<Map<String, Object>> listarReservasEfetuadas(String id_funcionario) {
        return jdbcTemplate.queryForList("select p.Nome , r2.id_reserva, r2.fk_Quarto_Numero, r2.Check_In, r2.Check_Out \n" +
                "from recepcionista r\n" +
                "join funcionario f on r.fk_Funcionario_Id_Funcionario = f.Id_Funcionario\n" +
                "join pessoa p on p.CPF = f.fk_Pessoa_CPF \n" +
                "join reservaclienterecepcionistaquarto r2 on r2.fk_Recepcionista_fk_Funcionario_Id_Funcionario = r.fk_Funcionario_Id_Funcionario "+
                "WHERE fk_Recepcionista_fk_Funcionario_Id_Funcionario = ?;", id_funcionario);
    }

}
