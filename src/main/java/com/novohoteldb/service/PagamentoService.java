package com.novohoteldb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.novohoteldb.repository.PagamentoRepository;

import java.util.List;
import java.util.Map;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Map<String, Object>> listarPagamentos(){
        return pagamentoRepository.listarPagamentos();
    }

    public Map<String, Object> checarPagamento(Integer id_reserva){
        return pagamentoRepository.checarPagamento(id_reserva);
    }

}
