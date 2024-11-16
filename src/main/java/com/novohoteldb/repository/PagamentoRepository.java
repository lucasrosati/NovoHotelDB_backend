package com.novohoteldb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class PagamentoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String,Object>> listarPagamentos(){
        String sql = "select * from pagamento";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String,Object> checarPagamento(Integer id_reserva){
        String sql = "select * from pagamento where fk_ReservaClienteRecepcionistaQuarto_id_reserva = ?";
        return jdbcTemplate.queryForMap(sql, id_reserva);
    }

    public void gerarPagamento(Integer id_reserva, long dias, Integer numQuarto) {
        String sql = "INSERT INTO pagamento (Pagamento_PK, Status, Data, Valor, fk_ReservaClienteRecepcionistaQuarto_id_reserva)" +
                "VALUES (?, ?, ?, ?, ?)";

        String consulta_quarto = "SELECT q.Diaria FROM quarto q WHERE q.NUMERO = ?";

        int valorQuarto = jdbcTemplate.queryForObject(consulta_quarto, Integer.class, numQuarto);

        int resultado = jdbcTemplate.update(sql,id_reserva, "Pendente", Date.valueOf(LocalDate.now()), (valorQuarto * dias), id_reserva);

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao gerar pagamento.");
        }

    }
}
