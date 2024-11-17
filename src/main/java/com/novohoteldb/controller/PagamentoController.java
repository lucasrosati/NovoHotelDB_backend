package com.novohoteldb.controller;

import com.novohoteldb.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;
    @GetMapping("/listar")
    public List<Map<String, Object>> listarPagamentos(){
        return pagamentoService.listarPagamentos();
    }

    @GetMapping("/{id_reserva}")
    public Map<String, Object> checarPagamento(@PathVariable Integer id_reserva){
        return pagamentoService.checarPagamento(id_reserva);
    }

    @PutMapping("/confirmar/{IdReserva}")
    public ResponseEntity<String> confirmarPagamento(@PathVariable Integer IdReserva){
        pagamentoService.confirmarPagamento(IdReserva);
        return ResponseEntity.ok("Pagamento confirmado");
    }

}
