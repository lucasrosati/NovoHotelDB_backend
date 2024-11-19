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
        String sql = "INSERT INTO pagamento (Status, Data, Valor, fk_ReservaClienteRecepcionistaQuarto_id_reserva)" +
                "VALUES (?, ?, ?, ?)";

        String consulta_quarto = "SELECT q.Diaria FROM quarto q WHERE q.NUMERO = ?";

        int valorQuarto = jdbcTemplate.queryForObject(consulta_quarto, Integer.class, numQuarto);

        int resultado = jdbcTemplate.update(sql, "Pendente", Date.valueOf(LocalDate.now()), (valorQuarto * dias), id_reserva);

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao gerar pagamento.");
        }

    }

    public void confirmarPagamento(int pagamentoPK){

        String sql = "UPDATE pagamento SET Status = ? WHERE Pagamento_PK = ?";

        String pagamento = "Select p.Status from pagamento p where Pagamento_PK = ?";
        String status = jdbcTemplate.queryForObject(pagamento, String.class, pagamentoPK);

        int idreserva = jdbcTemplate.queryForObject("SELECT p.fk_ReservaClienteRecepcionistaQuarto_id_reserva FROM pagamento p where pagamento_pk = ?", Integer.class, pagamentoPK);
        float valor = jdbcTemplate.queryForObject("SELECT p.Valor FROM pagamento p where pagamento_pk = ?", Float.class, pagamentoPK);


        if (status.equalsIgnoreCase("Pago")){
            throw new RuntimeException("Pagamento já confirmado");
        }

        int resultado = jdbcTemplate.update(sql, "Pago", pagamentoPK);

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao gerar pagamento.");
        }

        this.gerarNota(pagamentoPK, idreserva, valor);


    }

    public void gerarNota(int IdPagamento, int IdReserva, float Valor){
        String sql = "INSERT INTO nota(Codigo, Valor, CNPJ_Hotel, fk_ReservaClienteRecepcionistaQuarto_id_reserva) VALUES (?, ?, ?, ?)";
        String codigo = Integer.toString(IdPagamento*4567);
        int resultado = jdbcTemplate.update(sql,codigo, Valor, "123456", IdReserva);

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao gerar nota.");
        }

    }
}
