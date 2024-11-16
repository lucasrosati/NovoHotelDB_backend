package com.novohoteldb.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@Repository
public class NotaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> listarNotas() {
        String sql = "select * from nota";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> buscarNotaPorId(Integer id_reserva) {
        String sql = "select * from nota where fk_ReservaClienteRecepcionistaQuarto_id_reserva = ?";
        return jdbcTemplate.queryForMap(sql, id_reserva);
    }

}
