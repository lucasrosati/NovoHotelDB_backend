package com.novohoteldb.dto;

public record PagamentoDTO (
        String cpf,
        String numero,
        String cvv,
        String vencimento,
        String tipo
){
}
