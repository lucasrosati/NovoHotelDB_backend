package com.novohoteldb.dto;

public record PagamentoDTO (
        String cpf,
        String numero,
        Integer cvv,
        String vencimento,
        String tipo
){
}
