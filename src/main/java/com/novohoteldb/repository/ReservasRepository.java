package com.novohoteldb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ReservasRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> listarReservas() {
        String sql = "select * from ReservaClienteRecepcionistaQuarto";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> checarStatus(Integer idReserva) {
        String sql = "select r.id_reserva, p.Status " +
                "from reservaclienterecepcionistaquarto r " +
                "join pagamento p on p.fk_ReservaClienteRecepcionistaQuarto_id_reserva = r.id_reserva " +
                " where r.id_reserva = ?";
        return jdbcTemplate.queryForMap(sql, idReserva);
    }

    public void cancelarReserva(Integer idReserva) {

        String sqlPagamento = "DELETE FROM pagamento WHERE fk_ReservaClienteRecepcionistaQuarto_id_reserva = ?";
        jdbcTemplate.update(sqlPagamento, idReserva);

        String sql = "DELETE FROM reservaclienterecepcionistaquarto WHERE id_reserva = ?";
        int resultado = jdbcTemplate.update(sql, idReserva);

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao deletar a reserva com ID: " + idReserva);
        }
    }

    public void efetuarReserva(int idReserva, String cpfCliente, int recepcionistaId, int numeroQuarto, String dataCheckin, String dataCheckout) {
        String sql = "INSERT INTO reservaclienterecepcionistaquarto (id_reserva ,fk_Cliente_fk_Pessoa_CPF, fk_Recepcionista_fk_Funcionario_Id_Funcionario, fk_Quarto_Numero, check_in, check_out) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        int resultado = jdbcTemplate.update(sql, idReserva, cpfCliente, recepcionistaId, numeroQuarto, Date.valueOf(dataCheckin), Date.valueOf(dataCheckout));

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao efetuar a reserva.");
        }
    }

    public boolean verificarDisponibilidadeQuarto(int numeroQuarto, String dataCheckin, String dataCheckout) {

        String sql = "SELECT COUNT(*) FROM reservaclienterecepcionistaquarto " +
                "WHERE fk_Quarto_Numero = ? " +
                "AND (check_in BETWEEN ? AND ? OR check_out BETWEEN ? AND ? " +
                "OR ? BETWEEN check_in AND check_out OR ? BETWEEN check_in AND check_out)";

        int count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                numeroQuarto,
                Date.valueOf(dataCheckin), Date.valueOf(dataCheckout),
                Date.valueOf(dataCheckin), Date.valueOf(dataCheckout),
                Date.valueOf(dataCheckin), Date.valueOf(dataCheckout)
        );

        return count == 0;
    }

    public void atualizarReserva(Integer idReserva, String novoCheckIn, String novoCheckOut, Integer novoNumeroQuarto) {
        String sql = "UPDATE reservaclienterecepcionistaquarto " +
                "SET check_in = ?, check_out = ?, fk_Quarto_Numero = ? " +
                "WHERE id_reserva = ?";

        int resultado = jdbcTemplate.update(sql,
                Date.valueOf(novoCheckIn),
                Date.valueOf(novoCheckOut),
                novoNumeroQuarto,
                idReserva);

        if (resultado <= 0) {
            throw new RuntimeException("Erro ao atualizar a reserva com ID: " + idReserva);
        }
    }

}
