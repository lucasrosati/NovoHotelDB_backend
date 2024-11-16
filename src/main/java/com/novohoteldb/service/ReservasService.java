package com.novohoteldb.service;

import com.novohoteldb.dto.ReservaClienteRecepcionistaQuartoDTO;
import com.novohoteldb.repository.ReservasRepository;
import com.novohoteldb.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ReservasService {

    @Autowired
    private ReservasRepository reservasRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Map<String, Object>> listarReservas(){
        return reservasRepository.listarReservas();
    }

    public Map<String, Object> checarStatus(Integer idReserva){
        return reservasRepository.checarStatus(idReserva);
    }

    public void cancelarReserva(Integer idReserva){
        reservasRepository.cancelarReserva(idReserva);
    }

    public void efetuarReserva(ReservaClienteRecepcionistaQuartoDTO reserva) {

        boolean isQuartoDisponivel = reservasRepository.verificarDisponibilidadeQuarto(reserva.fk_Quarto_Numero(), reserva.Check_in(), reserva.Check_out());

        if (!isQuartoDisponivel) {
            throw new RuntimeException("Quarto não disponível para as datas selecionadas.");
        }

        LocalDate dataInicio = LocalDate.parse(reserva.Check_in());
        LocalDate dataFim = LocalDate.parse(reserva.Check_out());
        long diferencaEmDias = ChronoUnit.DAYS.between(dataInicio, dataFim);

        reservasRepository.efetuarReserva(reserva.id_reserva(), reserva.fk_Cliente_fk_Pessoa_CPF(), reserva.fk_Recepcionista_fk_Funcionario_Id_Funcionario(), reserva.fk_Quarto_Numero(), reserva.Check_in(), reserva.Check_out());
        pagamentoRepository.gerarPagamento(reserva.id_reserva(), diferencaEmDias, reserva.fk_Quarto_Numero());
    }

    public void atualizarReserva(int idReserva ,ReservaClienteRecepcionistaQuartoDTO reserva) {

        boolean isQuartoDisponivel = reservasRepository.verificarDisponibilidadeQuarto(reserva.fk_Quarto_Numero(), reserva.Check_in(), reserva.Check_out());

        if (!isQuartoDisponivel) {
            throw new RuntimeException("Quarto não disponível para as novas datas.");
        }

        reservasRepository.atualizarReserva(idReserva,
                reserva.Check_in(),
                reserva.Check_out(),
                reserva.fk_Quarto_Numero());
    }

}
