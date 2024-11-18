package com.novohoteldb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QuartosRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> listarQuartos(){
        return jdbcTemplate.queryForList("select * from quarto");
    }

    public Map<String, Object> consultarQuarto(String numero){
        String sql = "select * from quarto where Numero=?";
        return jdbcTemplate.queryForMap(sql, numero);
    }

    public List<Map<String, Object>> verificarReservas(String numero){
        String sql = "select fk_Quarto_Numero, Check_In, Check_Out \n" +
                "from reservaclienterecepcionistaquarto where fk_Quarto_Numero = ?";

        return jdbcTemplate.queryForList(sql, numero);

    }

    public void liberarQuarto(String numeroQuarto) {
        String sql = "UPDATE QUARTO SET STATUS = 0 WHERE Numero = ?";
        jdbcTemplate.update(sql, numeroQuarto);
    }

}
