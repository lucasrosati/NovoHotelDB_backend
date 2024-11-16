package com.novohoteldb.controller;

import com.novohoteldb.dto.ReservaClienteRecepcionistaQuartoDTO;
import com.novohoteldb.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @GetMapping("/listar")
    public List<Map<String, Object>> listarReservas(){
        return reservasService.listarReservas();
    }

    @GetMapping("/{idReserva}")
    public Map<String, Object> checarStatus(@PathVariable Integer idReserva){
        return reservasService.checarStatus(idReserva);
    }

    @DeleteMapping("/cancelar/{idReserva}")
    public ResponseEntity<String> cancelarReserva(@PathVariable Integer idReserva) {
        try {
            reservasService.cancelarReserva(idReserva);
            return ResponseEntity.status(200).body("Reserva cancelada com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/efetuar")
    public ResponseEntity<String> efetuarReserva(@RequestBody ReservaClienteRecepcionistaQuartoDTO reserva) {
        try {
            reservasService.efetuarReserva(reserva);
            return ResponseEntity.status(200).body("Reserva efetuada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{idReserva}")
    public ResponseEntity atualizarReserva(@PathVariable Integer idReserva, @RequestBody ReservaClienteRecepcionistaQuartoDTO reserva) {
        reservasService.atualizarReserva(idReserva,reserva);
        return ResponseEntity.status(200).body("Reserva atualizada com sucesso.");
    }

}
